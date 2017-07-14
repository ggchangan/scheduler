package cn.deepclue.scheduler.config;

import cn.deepclue.scheduler.exception.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * Created by luoyong on 17-3-28.
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(SchedulerException.class)
    @ResponseBody
    ResponseEntity<Object> handleSchedulerException(HttpServletRequest request, Throwable ex) {
        HttpStatus status = getStatus(request);
        SchedulerException schedulerException = (SchedulerException) ex;
        logger.error("处理http请求，出现Scheduler异常， 错误信息: " + schedulerException.getMessage(), ex);
        String responseMessage = getResopnseMessage(request, schedulerException);
        return new ResponseEntity<>(responseMessage, status);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    ResponseEntity<Object> handleControllerException(HttpServletRequest request, Throwable ex) {
        HttpStatus status = getStatus(request);
        logger.error("处理http请求，出现系统异常，错误信息: " + ex.getMessage(), ex);
        String responseMessage = "未知的异常错误。";
        return new ResponseEntity<>(responseMessage, status);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestPart(MissingServletRequestPartException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        StringBuilder errors = new StringBuilder();
        if (ex.getBindingResult().hasErrors()) {
            for (ObjectError error : ex.getBindingResult().getAllErrors()) {
                errors.append(error.getDefaultMessage()).append(System.getProperty("line.separator"));
            }
        }
        return handleExceptionInternal(ex, errors, headers, status, request);
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.error("处理http请求异常, 错误信息{}, body:{}", ex, body);
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    /**
     * 根据请求判断返回不同异常
     *
     * @param request
     * @param ex
     * @return
     */
    private String getResopnseMessage(HttpServletRequest request, SchedulerException ex) {
        String responseMessage;
        Locale locale = request.getLocale();
        if (locale.equals(Locale.CHINA)) {
            responseMessage = ex.getLocalizedMessage();
        } else {
            responseMessage = ex.getMessage();
        }
        return responseMessage;
    }
}
