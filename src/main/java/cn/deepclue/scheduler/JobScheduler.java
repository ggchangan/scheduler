package cn.deepclue.scheduler;

import java.util.List;

/**
 * Created by xuzb on 19/03/2017.
 */
public interface JobScheduler {
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

    /**
     * get all jobs in scheduler
     * @param page
     * @param pageSize
     * @return
     */
    List<Job> getJobs(int page, int pageSize);


    /**
     * get all jobs with special status
     * @param status see QJobStatus
     * @param page
     * @param pageSize
     * @return
     */
    List<Job> getJobs(QJobStatus status, int page, int pageSize);
}
