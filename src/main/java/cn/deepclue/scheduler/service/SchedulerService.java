package cn.deepclue.scheduler.service;


import cn.deepclue.scheduler.domain.ScheduleRequestVO;

public interface SchedulerService {
    boolean schedule(ScheduleRequestVO scheduleRequest);
}
