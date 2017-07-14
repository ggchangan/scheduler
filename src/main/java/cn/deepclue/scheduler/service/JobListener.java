package cn.deepclue.scheduler.service;

import cn.deepclue.scheduler.domain.QJobStatus;

/**
 * Created by magneto on 17-3-27.
 */
public interface JobListener {
    /**
     * A response handler.
     */
    void onStatusChanged(int jId, QJobStatus status);

    void onSuccess(int jId);

    /**
     * A failure handler.
     */
    void onFailure(int jId, Throwable e);
}
