package cn.deepclue.scheduler.service.impl.quartz;


import cn.deepclue.scheduler.domain.Job;
import cn.deepclue.scheduler.service.JobScheduler;
import cn.deepclue.scheduler.service.JobService;
import cn.deepclue.scheduler.domain.QJobStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuzb on 19/03/2017.
 */
public class QuartzJobService implements JobService {
    private JobScheduler jobScheduler;

    public void setJobScheduler(JobScheduler jobScheduler) {
        this.jobScheduler = jobScheduler;
        jobScheduler.start();
    }

    @Override
    public boolean start() {
        return false;
    }

    @Override public boolean shutdown() {
        return false;
    }

    @Override
    public boolean schedule(Job job) {
        return jobScheduler.schedule(job);
    }

    @Override
    public boolean unschedule(Job job) {
        return jobScheduler.unschedule(job);
    }

    @Override public boolean clear() {
        return jobScheduler.clear();
    }

    @Override
    public boolean resume(Job job) {
        return false;
    }

    @Override
    public boolean resumeAll() {
        return false;
    }

    @Override
    public List<Job> getJobs(int page, int pageSize) {
        return new ArrayList<>();
    }

    @Override
    public List<Job> getJobs(QJobStatus status, int page, int pageSize) {
        return null;
    }
}
