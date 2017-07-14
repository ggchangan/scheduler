package cn.deepclue.scheduler.service;


import cn.deepclue.scheduler.domain.Job;

public interface SchedulerService {
    boolean schedule(Job job);

    boolean unschedule(Job job);

    boolean clear();

    boolean resume(Job job);

    boolean resumeAll();
}
