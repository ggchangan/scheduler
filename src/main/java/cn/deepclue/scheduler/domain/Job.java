package cn.deepclue.scheduler.domain;

/**
 * Created by xuzb on 17/03/2017.
 */
public class Job {
    private int jId;
    private int appId;
    private Callback callback;

    public Job() {
    }

    public Job(int jId) {
        this.jId = jId;
    }

    public int getjId() {
        return jId;
    }

    public void setjId(int jId) {
        this.jId = jId;
    }

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    public String getGroupName() {
        return "DEFAULT";
    }

    public Callback getCallback() {
        return callback;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }
}
