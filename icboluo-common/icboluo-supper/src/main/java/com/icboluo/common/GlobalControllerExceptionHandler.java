package com.icboluo.common;

import com.icboluo.enumerate.ReEnum;
import com.icboluo.util.I18nException;
import com.icboluo.util.IcBoLuoException;
import com.icboluo.util.response.R;
import com.icboluo.util.response.Response;
import jakarta.annotation.Resource;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.UnexpectedTypeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Enumeration;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>拦截异常并统一处理
 * <p>全局异常处理，相当于controller层增加了一个异常捕获，全局返回值处理，已经脱离了web层，属于返回值包装；先全局异常处理，然后再全局返回值捕获
 * <p>@RestControllerAdvice 有时候不生效是因为指定了basePackages，这个指定规则不知道具体是什么样的，只要不指定扫描所有的包就可以了，
 * <p>GlobalControllerExceptionHandler 相当于 try catch，如果异常处理机制里面没有log.error(ex),则系统日志是查询不到异常堆栈日志的
 * <p>throw 不产生任何的日志, 仅仅只有log会产生日志
 *
 * @author icboluo
 */
@RestControllerAdvice
@Slf4j
public class GlobalControllerExceptionHandler {
    @Resource
    private HttpServletRequest request;

    @Resource
    private MessageSource messageSource;

    /**
     * 校验注解写错类型不匹配，单纯的后台校验器有问题
     *
     * @param e 异常类型
     * @return 失败的响应信息
     */
    @ExceptionHandler(value = {UnexpectedTypeException.class})
    public Response unexpectedTypeExceptionHandler(UnexpectedTypeException e) {
        // 全局异常处理需要增加log标识，否则找不到具体是哪一个异常捕获到了
        log.error("UnexpectedTypeException, Please check @valid code first", e);
        return R.error(ReEnum.SYSTEM_ERROR, messageSource);
    }

    /**
     * 前后端参数类型不匹配，反序列化异常
     *
     * @param e 异常类型
     * @return 失败的响应信息
     */
    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    public Response httpMessageNotReadableExceptionHandler(HttpMessageNotReadableException e) {
        log.error("HttpMessageNotReadableException: {}", ReEnum.UNEXPECTED_EXCEPTION, e);
        return R.error(ReEnum.INTERFACE_NOT_SUPPORT, messageSource);
    }

    /**
     * 异常处理,符合就近原则，先处理具体的异常，不能识别交给较大的异常
     *
     * @param e 异常信息
     * @return 失败的响应信息
     */
    @ExceptionHandler(value = {IcBoLuoException.class})
    // @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response icBoLuoExceptionHandler(IcBoLuoException e) {
        // 也可以使用这样的方式设置状态码，但是状态码只有200、400、500之类的有效，其他的都没用
        // response.setStatus(500);
        log.error("IcBoLuoException: ", e);
        return R.error(ReEnum.SYSTEM_ERROR, messageSource);
    }

    /**
     * 自定义国际化异常
     *
     * @param e 异常信息
     * @return 失败的响应信息
     */
    @ExceptionHandler(value = {I18nException.class})
    public Response icBoLuoI18nExceptionHandler(I18nException e) {
        log.error("IcBoLuoI18nException: ", e);
        return R.error(e.getMessage(), messageSource);
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public Response httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("HttpRequestMethodNotSupportedException: ", e);
        return R.error(String.valueOf(e.getStatusCode().value()), e.getMessage());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Response methodArgumentNotValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        String msg = fieldErrors.stream()
                .map(fieldError -> {
                    String field = fieldError.getField();
                    String defaultMessage = fieldError.getDefaultMessage();
                    assert defaultMessage != null;
                    String message = messageSource.getMessage(defaultMessage, null, LocaleContextHolder.getLocale());
                    return field + " " + message;
                }).collect(Collectors.joining(";"));
        log.error("MethodArgumentNotValidException: ", e);
        return R.error(msg);
    }

    /**
     * 参数异常
     *
     * @param e exception
     * @return msg
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public Response constraintViolationException(ConstraintViolationException e) {
        log.error("ConstraintViolationException: ", e);
        return R.error(e.getMessage());
    }

    /**
     * 如果异常不能被 RuntimeException 捕获，则为受检异常，需要显示捕获
     *
     * @param ex 异常
     * @return msg
     */
    @ExceptionHandler(value = RuntimeException.class)
    public Response runtimeExceptionHandler(RuntimeException ex) {
        log.error("RuntimeException: ", ex);
        return R.error(ReEnum.UNEXPECTED_EXCEPTION);
    }

    /**
     * 能跑到这里，没有被runtime捕获到，应该属于受检异常
     * <p>
     * 默认的异常处理方法，如果 e 有注解 @ResponseStatus 注解，则继续抛出，让框架处理
     *
     * @param e 异常信息
     * @return 失败的响应信息
     * @throws Exception {@code e} 最大异常
     */
    @ExceptionHandler(value = Exception.class)
    public Response exceptionHandler(Exception e) throws Exception {
        //如果异常上已经有 @ResponseStatus 注解，则让框架处理
        if (Objects.nonNull(AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class))) {
            throw e;
        }
        String msg = Objects.isNull(e.getMessage()) ? "操作失败，但没有失败消息" : e.getMessage();
        log.error("Exception: ", e);
        return R.error(Response.ERROR_CODE, msg);
    }


    /**
     * 打印 {@code request} 信息 和 {@code e} 信息
     *
     * @param e 错误信息
     */
    private void printLog(Throwable e) {
        if (log.isDebugEnabled()) {
            StringBuilder builder = new StringBuilder();
            builder.append(e.getMessage());
            builder.append("\nURL:").append(request.getRequestURL().toString()).append('\n');
            builder.append("Method:").append(request.getMethod()).append('\n');
            builder.append("Headers:\n");
            Enumeration<String> headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String name = headerNames.nextElement();
                builder.append(name).append(":").append(request.getHeader(name)).append('\n');
            }
            builder.append("Cookies:");
            Cookie[] cookies = request.getCookies();
            if (Objects.nonNull(cookies)) {
                for (Cookie cookie : cookies) {
                    builder.append(cookie.getName()).append(":").append(cookie.getValue()).append('\n');
                }
            }
            log.error("{}", builder, e);
        } else {
            log.error("{}", e.getMessage(), e);
        }
    }
}
