package cn.deepclue.scheduler;

import cn.deepclue.scheduler.domain.Callback;
import cn.deepclue.scheduler.domain.Job;
import cn.deepclue.scheduler.service.JobService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

/**
 * Created by ggchangan on 17-7-11.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JobServiceTest {
    @Autowired
    private JobService jobService;

    @Test
    public void scheduleTest() {
        Job job = new Job(1);
        job.setAppId(1);
        Callback callback = new Callback();
        callback.setUrl("http://www.baidu.com");
        callback.setRequestBody("{}");
        job.setCallback(callback);

        assertTrue(jobService.schedule(job));
    }
}
