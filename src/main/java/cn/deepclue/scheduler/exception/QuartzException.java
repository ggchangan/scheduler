package cn.deepclue.scheduler.exception;

/**
 * Created by magneto on 17-3-22.
 */
public class QuartzException extends SchedulerException {

    public QuartzException(String message, String localizedMessage) {
        super(message, localizedMessage);
    }

    public QuartzException(String message, String localizedMessage, Throwable cause) {
        super(message, localizedMessage, cause);
    }
}
