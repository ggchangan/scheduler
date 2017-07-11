package cn.deepclue.scheduler.exception;

/**
 * Created by sunxingwen on 17-5-10.
 */
public class SchedulerException extends RuntimeException {
    private final String localizedMessage;

    public SchedulerException(String message, String localizedMessage) {
        super(message);
        this.localizedMessage = localizedMessage;
    }

    public SchedulerException(String message, String localizedMessage, Throwable cause) {
        super(message, cause);
        this.localizedMessage = localizedMessage;
    }

    @Override
    public String getLocalizedMessage() {
        return localizedMessage;
    }
}
