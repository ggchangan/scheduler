package cn.deepclue.scheduler.service.impl;

import cn.deepclue.scheduler.domain.QJobStatus;
import cn.deepclue.scheduler.service.JobListener;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.listeners.JobListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by magneto on 17-3-27.
 */
@Service("qJobListener")
public class QJobListenerImpl extends JobListenerSupport {
    @Autowired
    private JobListener jobListener;

    @Override
    public String getName() {
        return "SchedulerJob";
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
        String jobIdStr = context.getJobDetail().getKey().getName();
        Integer jobId = Integer.valueOf(jobIdStr);
        jobListener.onStatusChanged(jobId, QJobStatus.RUNNING);
    }

    //任务结束后的处理函数
    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
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
