package cn.deepclue.scheduler.domain;

/**
 * Created by xuzb on 17/03/2017.
 */
public class Job {
    private int jId;

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

    public String getGroupName() {
        return "DEFAULT";
    }
}
