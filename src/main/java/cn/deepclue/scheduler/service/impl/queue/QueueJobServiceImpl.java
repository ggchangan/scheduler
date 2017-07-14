package cn.deepclue.scheduler.service.impl.queue;

import cn.deepclue.scheduler.domain.Job;
import cn.deepclue.scheduler.domain.QJobStatus;
import cn.deepclue.scheduler.service.JobScheduler;
import cn.deepclue.scheduler.service.JobService;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by ggchangan on 17-7-11.
 */
public class QueueJobServiceImpl implements JobService {
    public static final int MAX_JOB_COUNT = 5;

    private Queue<Job> jobQueue = new ArrayBlockingQueue<>(MAX_JOB_COUNT);

    private JobScheduler jobScheduler;

    public QueueJobServiceImpl() {
        jobScheduler = new QueueJobSchedulerImpl(jobQueue);
    }

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
