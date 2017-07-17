package cn.deepclue.scheduler.service.impl;

import cn.deepclue.scheduler.domain.QJobStatus;
import cn.deepclue.scheduler.service.JobListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("jobListener")
public class JobListenerImpl implements JobListener {
    private static Logger logger = LoggerFactory.getLogger(JobListenerImpl.class);

    @Override
    public void onStatusChanged(int jId, QJobStatus status) {
        logger.info("Task " + jId + " changed to " + status + ".");
    }

    @Override
    public void onSuccess(int jId) {
        logger.info("Task " + jId + " run successfully.");
    }

    @Override
    public void onFailure(int jId, Throwable e) {
        logger.error("Task " + jId + " failed {}", e);
    }
}
