package cn.deepclue.scheduler.config;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * Created by luoyong on 17-3-28.
 */
public class LogRequestInterceptor extends HandlerInterceptorAdapter {

    private Logger logger = LoggerFactory.getLogger(LogRequestInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String traceId = UUID.randomUUID().toString();
        RequestContextHolder.setTraceId(traceId);
        if (request.getParameterMap().isEmpty()) {
            logger.info("[{}]-[{}]", RequestContextHolder.getTraceId(), request.getRequestURI());
        } else {
            logger.info("[{}]-[{}]-[{}]", RequestContextHolder.getTraceId(), request.getRequestURI(), new Gson().toJson(request.getParameterMap()));
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        RequestContextHolder.reset();
    }
}
