package cn.deepclue.scheduler.impl.queue;

import cn.deepclue.scheduler.Job;
import cn.deepclue.scheduler.JobScheduler;
import cn.deepclue.scheduler.JobService;
import cn.deepclue.scheduler.QJobStatus;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.stream.Collectors;

import static cn.deepclue.scheduler.QJobStatus.*;

/**
 * Created by ggchangan on 17-7-11.
 */
public class QueueJobService implements JobService {
    public static final int MAX_JOB_COUNT = 5;

    private Queue<Job> jobQueue = new ArrayBlockingQueue<>(MAX_JOB_COUNT);

    private JobScheduler jobScheduler;

    public QueueJobService() {
        jobScheduler = new QueueJobScheduler(jobQueue);
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
