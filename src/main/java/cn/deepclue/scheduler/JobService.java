package cn.deepclue.scheduler;

import java.util.List;

/**
 * Created by xuzb on 19/03/2017.
 */
public interface JobService {
    /**
     * start scheduler
     * @return true if start successfully else false
     */
    boolean start();

    /**
     * shutdown sheduler and cleans up all resources associated with the scheduler
     * this method will return until all currently executing jobs have completed
     * @return true if haven't any exceptions
     */
    boolean shutdown();

    /**
     * add job to scheduler
     * @param job
     * @return true if job has been added to scheduler
     */
    boolean schedule(Job job);

    /**
     * unscheduler the job
     * @param job
     * @return true if unsheduler the job or the job is not contained in the sheduler
     */
    boolean unschedule(Job job);

    /**
     * clear all scheduling data - all jobs, all triggers
     * @return
     */
    boolean clear();

    /**
     * resume the job after scheduler process failed
     * @param job
     * @return
     */
    boolean resume(Job job);

    /**
     * resume all jobs after scheduler process failed
     * @return
     */
    boolean resumeAll();

    List<Job> getJobs(int page, int pageSize);
}
