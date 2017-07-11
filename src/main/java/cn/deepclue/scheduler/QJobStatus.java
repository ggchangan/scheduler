package cn.deepclue.scheduler;

/**
 * Created by magneto on 17-3-27.
 */
public enum QJobStatus {
    PENDING(0), RUNNING(1), FINISHED(2), CANCLE(3), FAILED(-1);

    private int value;

    QJobStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static QJobStatus getTaskStatus(int status) {
        switch (status) {
            case 0:
                return PENDING;
            case 1:
                return RUNNING;
            case 2:
                return FINISHED;
            case 3:
                return CANCLE;
            case -1:
                return FAILED;

            default:
                throw new IllegalStateException("Unknown task status: " + status);
        }
    }
}
