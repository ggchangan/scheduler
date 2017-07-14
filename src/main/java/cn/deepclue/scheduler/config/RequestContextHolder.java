package cn.deepclue.scheduler.config;

import org.springframework.core.NamedThreadLocal;

/**
 * Created by luoyong on 17-3-28.
 */
public class RequestContextHolder {

    /**
     * 追踪标识
     */
    private static final ThreadLocal<String> TRACE_ID = new NamedThreadLocal<>("current request context");

    public static void reset() {
        TRACE_ID.remove();
    }

    public static void setTraceId(String traceId) {
        if (traceId == null) {
            reset();
        } else {
            TRACE_ID.set(traceId);
        }
    }

    public static String getTraceId() {
        return TRACE_ID.get();
    }

}
