package cn.deepclue.scheduler.service.impl.quartz;


import cn.deepclue.scheduler.domain.Job;
import cn.deepclue.scheduler.domain.QJobStatus;
import cn.deepclue.scheduler.service.JobScheduler;
import cn.deepclue.scheduler.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xuzb on 19/03/2017.
 */
@Service("jobService")
public class QuartzJobServiceImpl implements JobService {
    @Autowired
    private JobScheduler jobScheduler;

    @Override
    public boolean start() {
        return jobScheduler.start();
    }

    @Override
    public boolean shutdown() {
        return jobScheduler.shutdown();
    }

    @Override
    public boolean schedule(Job job) {
        return jobScheduler.schedule(job);
    }

    @Override
    public boolean unschedule(Job job) {
        return jobScheduler.unschedule(job);
    }

    @Override
    public boolean clear() {
        return jobScheduler.clear();
    }

    @Override
    public boolean resume(Job job) {
        return jobScheduler.resume(job);
    }

    @Override
    public boolean resumeAll() {
        return jobScheduler.resumeAll();
    }

    @Override
    public List<Job> getJobs(int page, int pageSize) {
        return jobScheduler.getJobs(page, pageSize);
    }

    @Override
    public List<Job> getJobs(QJobStatus status, int page, int pageSize) {
        return jobScheduler.getJobs(status, page, pageSize);
    }
}
