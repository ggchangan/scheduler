package cn.deepclue.scheduler.service.impl.queue;

import cn.deepclue.scheduler.domain.Job;
import cn.deepclue.scheduler.domain.QJobStatus;
import cn.deepclue.scheduler.service.JobScheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.stream.Collectors;

/**
 * Created by ggchangan on 17-7-11.
 */
public class QueueJobSchedulerImpl implements JobScheduler {
    private static Logger logger = LoggerFactory.getLogger(QueueJobSchedulerImpl.class);

    private Queue<Job> jobQueue;
    private volatile boolean shutdown = false;
    private List<Job> scheduledJobs = new LinkedList<>();

    public QueueJobSchedulerImpl(Queue<Job> jobQueue) {
        this.jobQueue = jobQueue;
    }

    @Override
    public boolean start() {
        if (jobQueue == null) {
            jobQueue = new ArrayBlockingQueue<>(QueueJobServiceImpl.MAX_JOB_COUNT);
        }
        Thread schedulerWorker = new Thread(new SchedulerWorker());
        schedulerWorker.start();

        return true;
    }

    @Override
    public boolean shutdown() {
        jobQueue.clear();
        shutdown = true;
        return (jobQueue != null && jobQueue.isEmpty());
    }

    @Override
    public boolean schedule(Job job) {
        return jobQueue.offer(job);
    }

    @Override
    public boolean unschedule(Job job) {
        return jobQueue.remove(job);
    }

    @Override
    public boolean clear() {
        jobQueue.clear();
        return (jobQueue == null || jobQueue.isEmpty());
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
        //TODO page, pageSize
        return jobQueue.stream().collect(Collectors.toList());
    }

    @Override
    public List<Job> getJobs(QJobStatus status, int page, int pageSize) {
        switch (status) {
            case RUNNING:
                return jobQueue.stream().collect(Collectors.toList());
            case FINISHED:
                return scheduledJobs;
        }

        return scheduledJobs;
    }

    public class SchedulerWorker implements Runnable {

        @Override
        public void run() {
            while (!shutdown) {
                Job job = jobQueue.poll();
                if (job != null) {
                    scheduledJobs.add(job);
                }

                try {
                    //暂停1s后开始调度
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    logger.error(e.getMessage(), e);
                }

            }
        }
    }
}
