package com.moais.todo.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RequestUtil {

    //현재 요청에 대한 Request 가져오기
    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    }

    //요청한 클라이언트의 아이피 가져오기
    public static String getClientIP() {
        HttpServletRequest request = getRequest();
        String ip = request.getHeader("X-FORWARD_For");
        if(ip == null) ip = request.getHeader("Proxy-Client-IP");
        if(ip == null) ip = request.getHeader("WL-Proxy-Client-IP");
        if(ip == null) ip = request.getHeader("HTTP_Client_IP");
        if(ip == null) ip = request.getHeader("HTTP_X_FORWARD_FOR");
        if(ip == null) ip = request.getRemoteAddr();
        return ip;
    }

    //Args 로부터 파라미터를 추출
    public static String getRequestArgs(Object[] args) {
        List<String> collect = Arrays.stream(args)
                .map(o -> o.toString())
                .collect(Collectors.toList());
        return collect.size()==0?"":collect.get(0);
    }

    //Object 의 파라미터중 null 인 파라미터가 있을 경우 true
    public static boolean isParamNull(Object vo) throws Exception {
        Field[] fields = vo.getClass().getDeclaredFields();
        Method method = null;
        for(Field f : fields) {
            method = vo.getClass().getMethod("get" + f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1));
            String value = String.valueOf(method.invoke(vo));
            if(value==null || value.equals("null")) return true;
        }
        return false;
    }


}
