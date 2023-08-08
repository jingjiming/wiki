package com.css.wiki.config;

import com.css.common.beans.enums.ResultCode;
import com.css.common.beans.response.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

    private static final Logger logger = LoggerFactory.getLogger(GlobalException.class);

    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public JsonResult<?> handleException(BindException e) {
        logger.warn("Exception[参数校验失败:{}]", e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        //e.printStackTrace();
        return JsonResult.badRequest(e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JsonResult<?> handleException(Exception e) {
        logger.error("Exception[message:{}]", e.toString());
        e.printStackTrace();
        return JsonResult.badRequest(ResultCode.ERROR.getMessage());
    }
}
