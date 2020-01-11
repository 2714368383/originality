package com.language.originality.controller;

import com.language.originality.commons.ResultModel;
import com.language.originality.ecceptions.ApplicationException;
import com.language.originality.enums.ResultStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ValidationException;

/**
 * 全局异常处理
 *
 * @author hughes
 */
@Slf4j
@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(ValidationException.class)
    public ResultModel handleValidatorException(ValidationException validatorException) {
        log.info("传入参数异常");
        return ResultModel.fail(ResultStatusEnum.PARAM_VALIDATOR, validatorException.getMessage());
    }

    /**
     * 处理自定义异常
     *
     * @param applicationException 自定义异常
     * @return 返回异常信息
     * @author hughes
     */
    @ExceptionHandler(ApplicationException.class)
    public ResultModel handleApplicationException(ApplicationException applicationException) {
        log.info("自定义异常全局处理");
        return ResultModel.fail(applicationException.getErrorCode(), applicationException.getMessage());
    }

    /**
     * 处理未捕获异常
     *
     * @param request request
     * @param e       异常
     * @return 返回http状态码及异常信息
     * @author hughes
     */
    @ExceptionHandler(Exception.class)
    public ResultModel handleException(HttpServletRequest request, Exception e) {
        log.info("未捕获异常全局处理");
        log.error(e.getMessage(), e);
        return ResultModel.fail(getHttpStatus(request).value(), "系统异常");
    }

    /**
     * 获取http状态码
     *
     * @param request request
     * @return 返回http状态码
     * @author hughes
     */
    private HttpStatus getHttpStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}
