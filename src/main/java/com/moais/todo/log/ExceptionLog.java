package com.moais.todo.log;

import com.moais.todo.util.RequestUtil;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.io.StringWriter;

@Slf4j
public class ExceptionLog<T> {

    private Exception e;
    private T vo;

    public ExceptionLog(Exception e, T vo) {
        this.e = e;
        this.vo = vo;
    }

    public void logging() {
        HttpServletRequest request = RequestUtil.getRequest();
        String request_url = request.getRequestURI().toString();
        String request_ip = RequestUtil.getClientIP();

        StringWriter errors = new StringWriter();
        e.printStackTrace();

        log.error("[" + request_url + "]");
        log.error("[" + request_ip + "]");
        log.error("[" + e.toString() + "]");
        log.error("[" + (vo==null?"":vo.toString()) + "]");
        log.error("Exception message - " + errors.toString());
    }
}
