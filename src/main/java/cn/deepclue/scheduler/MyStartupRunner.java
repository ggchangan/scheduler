package cn.deepclue.scheduler;

import cn.deepclue.scheduler.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = 1)
public class MyStartupRunner implements CommandLineRunner {
    @Autowired
    private JobService jobService;

    @Override
    public void run(String... args) throws Exception {
        jobService.start();
    }
}
