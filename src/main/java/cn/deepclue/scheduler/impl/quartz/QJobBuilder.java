package cn.deepclue.scheduler.impl.quartz;

import cn.deepclue.scheduler.Job;
import org.quartz.JobDetail;
import org.quartz.Trigger;

/**
 * Created by magneto on 17-3-22.
 */
public interface QJobBuilder {
    Trigger buildTrigger(Job job);
    JobDetail buildJobDetail(Job job);
}
