package cn.deepclue.scheduler;

import cn.deepclue.scheduler.service.JobService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {
    public void onApplicationEvent(ContextRefreshedEvent event) {
        JobService jobService = event.getApplicationContext().getBean(JobService.class);
        jobService.start();
    }
}
