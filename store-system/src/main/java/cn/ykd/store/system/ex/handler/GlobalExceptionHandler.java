package cn.ykd.store.system.ex.handler;

import cn.ykd.store.system.ex.ServiceException;
import cn.ykd.store.system.web.JsonResult;
import cn.ykd.store.system.web.ServiceCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;

/**
 * 异常统一处理
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public JsonResult<Void> handleServiceException(ServiceException e){
        log.warn("程序允许过程中出现了handleServiceException,将统一处理",e.getMessage());
        log.warn("异常信息：{}",e.getMessage());
        return JsonResult.fail(e);
    }

    @ExceptionHandler
    public JsonResult<Void> handleServiceException(BindException e){
        log.warn("程序允许过程中出现了BindException:{},将统一处理");
        log.warn("异常信息：{}",e.getMessage());
//        StringJoiner stringJoiner = new StringJoiner(",","请求参数格式错误:","!");
//        List<FieldError> fieldErrors = e.getFieldErrors();
//        for (FieldError fieldError : fieldErrors){
//            stringJoiner.add(fieldError.getDefaultMessage());
//        }
//        return JsonResult.fail(ServiceCode.ERR_BAD_REQUEST,stringJoiner.toString());
        return JsonResult.fail(ServiceCode.ERR_BAD_REQUEST,e.getFieldError().getDefaultMessage());
    }


    @ExceptionHandler
    public JsonResult<Void> handleConstraintViolationException(ConstraintViolationException e){
        log.warn("程序允许过程中出现了ConstraintViolationException,将统一处理",e.getMessage());
        log.warn("异常信息：{}",e.getMessage());
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        String message="";
       for(ConstraintViolation<?> constraintViolation  : constraintViolations){
           message = constraintViolation.getMessage();
       }
        return JsonResult.fail(ServiceCode.ERR_BAD_REQUEST, message);
    }

    @ExceptionHandler({InternalAuthenticationServiceException.class,
            BadCredentialsException.class})
    public JsonResult<Void> handleAuthenticationException(AuthenticationException e) {
        log.warn("程序运行过程中出现AuthenticationException，将统一处理！");
        log.warn("异常信息：{}", e.getMessage());
        String message = "登录失败，用户名或密码错误！";
        return JsonResult.fail(ServiceCode.ERR_UNAUTHORIZED, message);
    }

    @ExceptionHandler
    public JsonResult<Void> handleDisabledException(DisabledException e) {
        log.warn("程序运行过程中出现DisabledException，将统一处理！");
        log.warn("异常信息：{}", e.getMessage());
        String message = "登录失败，此账号已经被禁用！";
        return JsonResult.fail(ServiceCode.ERR_UNAUTHORIZED_DISABLED, message);
    }

    @ExceptionHandler
    public JsonResult<Void> handleAccessDeniedException(AccessDeniedException e) {
        log.warn("程序运行过程中出现AccessDeniedException，将统一处理！");
        log.warn("异常信息：{}", e.getMessage());
        String message = "禁止访问，您当前登录的账号无此操作权限！";
        return JsonResult.fail(ServiceCode.ERR_FORBIDDEN, message);
    }













    //注意 此方法意义：避免因为某个异常未处理，导致服务器响应500结果
    //注意: e. printStackTraceC通常是禁止使用的，因为其输出方式是阻塞式的!
    //以下方法中使用了此语句，是因为用于发现错误，并不断的补充处理对应的异常的方法
    //随着开发进度的推进，执行到以下方法的概率会越来越低,出现由于此语句导致的问题的概率也会越来越低,
    //甚至补充足够多的处理异常的方法后，根本就不会执行到以下方法了
    //项目上线注释此语句
    @ExceptionHandler
    public JsonResult<Void> handleThrowable(Throwable e){
        log.warn("程序允许过程中出现了handleThrowable,将统一处理",e.getMessage());
        log.warn("异常类型：{}",e.getClass().getName());
        log.warn("异常信息：{}",e.getMessage());
        String message="【开发阶段专用】你的服务器异常，请检查服务器控制台中的异常信息！并补充处理方法";
        // String message="服务器忙，请稍后再试“;
        e.printStackTrace();//打印异常的跟踪信息
        return JsonResult.fail(ServiceCode.ERR_UNJOWN,message);
    }
}
