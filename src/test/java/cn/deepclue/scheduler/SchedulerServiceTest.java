package cn.deepclue.scheduler;

import cn.deepclue.scheduler.service.SchedulerService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by ggchangan on 17-7-11.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SchedulerServiceTest {
    @Autowired
    private SchedulerService service;
}
