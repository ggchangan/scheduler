package cn.deepclue.scheduler.service.impl.quartz;


import cn.deepclue.scheduler.domain.Job;
import cn.deepclue.scheduler.service.impl.quartz.qjob.QScheduleJob;
import org.quartz.*;

/**
 * Created by magneto on 17-3-24.
 */
public class QStreamJobBuilder implements QJobBuilder {
    public static final String JOB_CLASS_KEY = "JOB_CLASS";
    public static final String JOB_DATA_KEY = "JOB_DATA";

    public static final int FUTURE_TIME = 1;

    public QStreamJobBuilder() {}

    @Override
    public Trigger buildTrigger(Job job) {
        TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();

        return triggerBuilder.withIdentity(String.valueOf(job.getjId()), job.getGroupName())
                .startAt(DateBuilder.futureDate(FUTURE_TIME, DateBuilder.IntervalUnit.SECOND)).build();
    }

    @Override
    public JobDetail buildJobDetail(Job job) {
        String jobData = job.serialize();

        return JobBuilder.newJob(QScheduleJob.class)
                .withIdentity(String.valueOf(job.getjId()), job.getGroupName())
                .usingJobData(JOB_CLASS_KEY, job.getClass().getName())
                .usingJobData(JOB_DATA_KEY, jobData)
                .build();
    }
}
