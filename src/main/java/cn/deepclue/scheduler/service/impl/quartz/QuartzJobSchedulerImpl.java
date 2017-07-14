package cn.deepclue.scheduler.service.impl.quartz;

import cn.deepclue.scheduler.domain.Job;
import cn.deepclue.scheduler.domain.QJobStatus;
import cn.deepclue.scheduler.exception.QuartzException;
import cn.deepclue.scheduler.service.JobListener;
import cn.deepclue.scheduler.service.JobScheduler;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.quartz.impl.matchers.EverythingMatcher.allJobs;

/**
 * Created by xuzb on 19/03/2017.
 */
@Service("jobScheduler")
public class QuartzJobSchedulerImpl implements JobScheduler {
    private static Logger logger = LoggerFactory.getLogger(QuartzJobSchedulerImpl.class);

    private Scheduler scheduler;
    private JobListener jobListener;
    private QJobBuilder qJobBuilder;

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public void setqJobBuilder(QJobBuilder qJobBuilder) {
        this.qJobBuilder = qJobBuilder;
    }

    public QJobBuilder getqJobBuilder() {
        return qJobBuilder;
    }

    public Scheduler getScheduler() {
        return scheduler;
    }

    @Override
    public boolean schedule(Job job) {
        if (qJobBuilder == null) {
            throw new QuartzException("Please set job builder.", "请设置任务构建的工厂函数。");
        }

        try {

            if (scheduler == null || !scheduler.isStarted()) {
                throw new QuartzException("Please start Quartz scheduler.", "请先启动Quartz调度器。");
            }

            JobDetail jobDetail = qJobBuilder.buildJobDetail(job);
            Trigger trigger = qJobBuilder.buildTrigger(job);

            scheduler.scheduleJob(jobDetail, trigger);
            if (jobListener != null) {
                jobListener.onStatusChanged(job.getjId(), QJobStatus.PENDING);
            }

            return true;
        } catch (SchedulerException e) {
            throw new QuartzException("Filed to build job scheduler.", "构建可调度任务失败。", e);
        }
    }

    /**
     * 指定的任务不存在直接返回true
     *
     * @param job
     * @return
     */
    @Override
    public boolean unschedule(Job job) {
        JobKey jobKey = new JobKey(String.valueOf(job.getjId()), job.getGroupName());
        try {
            if (scheduler.checkExists(jobKey)) {
                return deleteJob(jobKey);
            } else {
                logger.warn(String.format("%s任务不存在。", jobKey));
            }
        } catch (SchedulerException e) {
            logger.info(e.getMessage(), e);
            return deleteJob(jobKey);
        }

        return true;
    }

    public void addListener(JobListener jobListener) {
        this.jobListener = jobListener;
    }

    @Override
    public boolean start() {
        try {
            if (scheduler == null) {
                scheduler = StdSchedulerFactory.getDefaultScheduler();
            }

            if (!scheduler.isStarted()) {
                scheduler.start();
            }

            if (jobListener != null) {
                QJobListener qJobListener = new QJobListener(jobListener);
                scheduler.getListenerManager().addJobListener(qJobListener, allJobs());
            }

            return true;
        } catch (SchedulerException e) {
            throw new QuartzException("Filed to start Quartz scheduler.", "启动Quartz调度器失败。", e);
        }
    }

    @Override
    public boolean shutdown() {
        if (scheduler == null) {
            return false;
        }

        try {
            if (scheduler.isShutdown()) {
                return true;
            }

            scheduler.shutdown(true);
            scheduler = null;

            return true;
        } catch (SchedulerException e) {
            throw new QuartzException("Filed to close Quartz scheduler.", "关闭Quartz调度器失败。", e);
        }
    }

    @Override
    public boolean clear() {
        try {
            scheduler.clear();
            return true;
        } catch (SchedulerException e) {
            throw new QuartzException("Filed to clear Quartz scheduler.", "清除Quartz调度器失败。", e);
        }
    }

    @Override
    public boolean resume(Job job) {
        return false;
    }


    /**
     * @param jId   任务标识
     * @param group 任务所在组，需要给出才能删除相应任务
     * @return 任务不存在或无法删除返回false，任务存在并且正确删除返回true
     */
    public boolean deleteJob(int jId, String group) {
        JobKey jobKey = new JobKey(String.valueOf(jId), group);
        return deleteJob(jobKey);
    }

    private boolean deleteJob(JobKey jobKey) {
        boolean deleteStatus;
        try {
            deleteStatus = scheduler.deleteJob(jobKey);
            if (deleteStatus && jobListener != null) {
                Integer jobId = Integer.valueOf(jobKey.getName());
                jobListener.onStatusChanged(jobId, QJobStatus.CANCLE);
            }
        } catch (SchedulerException e) {
            throw new QuartzException(String.format("Failed to delete job %s.", jobKey.getName()),
                    String.format("删除任务%s，失败。", jobKey.getName()), e);
        }

        return deleteStatus;
    }

    /**
     * 恢复所有任务，当调度服务终止后，重启服务需要运行此方法
     *
     * @return
     */
    public boolean resumeAll() {
        try {
            if (scheduler == null || !scheduler.isStarted()) {
                throw new QuartzException("Please start Quartz scheduler.", "请先启动Quartz调度器。");
            }
        } catch (SchedulerException e) {
            logger.warn(e.getMessage(), e);
            throw new QuartzException("Please start Quartz scheduler.", "请先启动Quartz调度器。", e);
        }

        try {
            scheduler.resumeAll();
        } catch (SchedulerException e) {
            throw new QuartzException("Filed to resume Quartz scheduler", "恢复任务调度失败。", e);
        }

        return true;
    }

    @Override
    public List<Job> getJobs(int page, int pageSize) {
        return null;
    }

    @Override
    public List<Job> getJobs(QJobStatus status, int page, int pageSize) {
        return null;
    }

    public boolean resumeGroup(String group) {
        try {
            if (scheduler == null || !scheduler.isStarted()) {
                throw new QuartzException("Please start Quartz scheduler.", "请先启动Quartz调度器。");
            }
        } catch (SchedulerException e) {
            logger.warn(e.getMessage(), e);
            throw new QuartzException("Please start Quartz scheduler.", "请先启动Quartz调度器。", e);
        }

        try {
            scheduler.resumeTriggers(GroupMatcher.<TriggerKey>groupEquals(group));
        } catch (SchedulerException e) {
            logger.warn(e.getMessage(), e);
            throw new QuartzException(String.format("Failed to resume %s type job scheduler.", group),
                    String.format("恢复%s类型任务调度失败。", group), e);
        }

        return true;
    }
}

