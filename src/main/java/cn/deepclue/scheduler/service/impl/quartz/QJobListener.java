package cn.deepclue.scheduler.service.impl.quartz;

import cn.deepclue.scheduler.service.JobListener;
import cn.deepclue.scheduler.domain.QJobStatus;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.listeners.JobListenerSupport;

/**
 * Created by magneto on 17-3-27.
 */
public class QJobListener extends JobListenerSupport {
    private JobListener jobListener;

    public QJobListener(JobListener jobListener) {
        this.jobListener = jobListener;
    }

    @Override public String getName() {
        return "SchedulerJob";
    }


    @Override public void jobToBeExecuted(JobExecutionContext context) {
        String jobIdStr = context.getJobDetail().getKey().getName();
        Integer jobId = Integer.valueOf(jobIdStr);
        jobListener.onStatusChanged(jobId, QJobStatus.RUNNING);
    }

    //任务结束后的处理函数
    @Override public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        //所有执行的任务抛出JobExecutionException异常，根据异常是否为空，可判定任务运行过程中，是否产生了异常
        String jobIdStr = context.getJobDetail().getKey().getName();
        Integer jobId = Integer.valueOf(jobIdStr);
        if (jobException == null) {
            Boolean runState = (Boolean) context.getResult();
            if (runState != null && runState) {
                jobListener.onStatusChanged(jobId, QJobStatus.FINISHED);
                jobListener.onSuccess(jobId);
            } else {
                jobListener.onStatusChanged(jobId, QJobStatus.FAILED);
            }
        } else {
            jobListener.onStatusChanged(jobId, QJobStatus.FAILED);
            jobListener.onFailure(jobId, jobException);
        }

    }
}
