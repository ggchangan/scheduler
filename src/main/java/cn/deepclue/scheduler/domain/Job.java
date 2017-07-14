package cn.deepclue.scheduler.domain;

/**
 * Created by xuzb on 17/03/2017.
 * Executable job for running.
 */
public abstract class Job {
    protected int jId;

    public Job() {}
    public Job(int jId) {
        this.jId = jId;
    }

    public abstract boolean run();

    public int getjId() {
        return jId;
    }

    public void setjId(int jId) {
        this.jId = jId;
    }

    public String getGroupName() {
        return "DEFAULT";
    }

    public abstract String serialize();

    public abstract void deserialize(String jsonMap);
}
