package com.moais.todo.handler;

import com.moais.todo.log.ExceptionLog;
import com.moais.todo.util.RequestUtil;
import com.moais.todo.vo.ResponseVO;
import com.moais.todo.vo.ResultCode;
import org.springframework.core.annotation.Order;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice("com.moais.todo.controller")
public class ExceptionAdvice {

    @Order(Ordered.LOWEST_PRECEDENCE)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseVO common(Exception e) {
        String body = RequestUtil.getRequestBody();
        new ExceptionLog(e, body).logging();
        return new ResponseVO(ResultCode.ERROR_CODE, "에러");
    }

    @Order(Ordered.HIGHEST_PRECEDENCE)
    @ExceptionHandler(MissingRequestHeaderException.class)
    @ResponseBody
    public ResponseVO missingHeader(Exception e) {
        String body = RequestUtil.getRequestBody();
        new ExceptionLog(e, body).logging();
        return new ResponseVO(ResultCode.NULL_ERROR, "헤더값 누락");
    }

    @Order(Ordered.HIGHEST_PRECEDENCE)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    public ResponseVO typeMismatch(Exception e) {
        String body = RequestUtil.getRequestBody();
        new ExceptionLog(e, body).logging();
        return new ResponseVO(ResultCode.TYPE_ERROR, "구분값이 올바르지 않습니다.");
    }
}
