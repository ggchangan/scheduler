package cn.deepclue.scheduler.queue;

import cn.deepclue.scheduler.Job;
import cn.deepclue.scheduler.JobService;
import cn.deepclue.scheduler.QJobStatus;
import cn.deepclue.scheduler.SchedulerService;
import cn.deepclue.scheduler.impl.queue.QueueJobService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by ggchangan on 17-7-11.
 */
public class QueueJobSchedulerIntegTest {
    SchedulerService service = new SchedulerService();
    JobService jobService = new QueueJobService();

    @Before
    public void setUp() {
        service.setService(jobService);
        service.start();
    }

    @After
    public void tearDown() {
        service.shutdown();
    }

    @Test
    public void schedulerTest() {
        //TODO 管理接口和调度接口分开

        Job job1 = new TestJob(1);
        jobService.schedule(job1);
        Job job2 = new TestJob(2);
        jobService.schedule(job2);
        List<Job> jobs = jobService.getJobs(0, 10);
        assertTrue(jobs.size() == 1);
        System.out.println(jobs.get(0).toString());
        List<Job> scheduledJobs = jobService.getJobs(QJobStatus.FINISHED, 0, 10);
        assertTrue(scheduledJobs.size() == 1);
        System.out.println(scheduledJobs.get(0).toString());
        jobService.clear();
    }

    class TestJob extends Job {

        public TestJob(int jId) {
            super(jId);
        }

        @Override
        public boolean run() {
            return false;
        }

        @Override
        public String serialize() {
            return null;
        }

        @Override
        public void deserialize(String jsonMap) {

        }

        @Override
        public String toString() {
            return String.format("JId:%d", jId);
        }
    }
}
