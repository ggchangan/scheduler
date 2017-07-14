package cn.deepclue.scheduler.service.impl;

import cn.deepclue.scheduler.domain.Job;
import cn.deepclue.scheduler.service.SchedulerService;
import org.springframework.stereotype.Service;

@Service("schedulerService")
public class SchedulerServiceImpl implements SchedulerService {
    @Override
    public boolean schedule(Job job) {
        return false;
    }

    @Override
    public boolean unschedule(Job job) {
        return false;
    }

    @Override
    public boolean clear() {
        return false;
    }

    @Override
    public boolean resume(Job job) {
        return false;
    }

    @Override
    public boolean resumeAll() {
        return false;
    }
}
