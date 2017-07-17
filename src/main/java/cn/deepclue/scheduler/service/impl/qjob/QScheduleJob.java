package cn.deepclue.scheduler.service.impl.qjob;

import cn.deepclue.scheduler.domain.Job;
import cn.deepclue.scheduler.exception.SchedulerException;
import cn.deepclue.scheduler.service.impl.builder.QStreamJobBuilderImpl;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;

/**
 * Created by xuzb on 17/05/2017.
 */
public class QScheduleJob implements QJob {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(QScheduleJob.class);
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();

        String jobClassName = jobDataMap.getString(QStreamJobBuilderImpl.JOB_CLASS_KEY);

        Job job;
        try {
            Class<?> jobClass = Class.forName(jobClassName);
            job = (Job) jobClass.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            logger.info("{}", e);
            throw new SchedulerException("Unkonwn job class name: " + jobClassName, "未知的异常类名: " + jobClassName);
        }

        int jId = Integer.valueOf(context.getJobDetail().getKey().getName());
        job.setjId(jId);

        String jobData = jobDataMap.getString(QStreamJobBuilderImpl.JOB_DATA_KEY);
        job.deserialize(jobData);

        try {
            boolean retState = job.run();
            context.setResult(retState);
        } catch (Exception e) {
            JobExecutionException je = new JobExecutionException(e);

            // 添加此时异常处理代码，例如直接取消此任务运行，或者等待一段时间后运行
            je.setUnscheduleAllTriggers(true);

            // We can use je.refireImmediately() to refire this job.

            throw je;
        }
    }
}
