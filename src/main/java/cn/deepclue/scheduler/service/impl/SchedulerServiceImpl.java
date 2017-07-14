package cn.deepclue.scheduler.service.impl;

import cn.deepclue.scheduler.domain.ScheduleRequestVO;
import cn.deepclue.scheduler.service.SchedulerService;
import org.springframework.stereotype.Service;

@Service("schedulerService")
public class SchedulerServiceImpl implements SchedulerService {
    @Override
    public boolean schedule(ScheduleRequestVO scheduleRequest) {
        return false;
    }
}
