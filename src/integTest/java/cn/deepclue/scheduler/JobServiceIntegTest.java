package cn.deepclue.scheduler;

import cn.deepclue.scheduler.domain.Job;
import cn.deepclue.scheduler.domain.QJobStatus;
import cn.deepclue.scheduler.service.JobService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by ggchangan on 17-7-11.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JobServiceIntegTest {
    @Autowired
    private JobService jobService;

    @Before
    public void setUp() {
        jobService.start();
    }

    @Test
    public void schedulerTest() {
        //TODO 管理接口和调度接口分开
        Job job1 = new Job(1);
        jobService.schedule(job1);
        Job job2 = new Job(2);
        jobService.schedule(job2);
        List<Job> jobs = jobService.getJobs(0, 10);
        assertTrue(jobs.size() == 1);
        System.out.println(jobs.get(0).toString());
        List<Job> scheduledJobs = jobService.getJobs(QJobStatus.FINISHED, 0, 10);
        assertTrue(scheduledJobs.size() == 1);
        System.out.println(scheduledJobs.get(0).toString());
        jobService.clear();
    }

    @After
    public void tearDown() {
        jobService.shutdown();
    }
}
