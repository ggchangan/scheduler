package cn.deepclue.scheduler.web;

import cn.deepclue.scheduler.domain.ScheduleRequestVO;
import cn.deepclue.scheduler.service.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SchedulerController {
    @Autowired
    private SchedulerService schedulerService;

    @RequestMapping(path = "/schedule", method = RequestMethod.POST)
    public boolean schedule(@RequestBody ScheduleRequestVO scheduleRequest) {
        return schedulerService.schedule(scheduleRequest);
    }
}
