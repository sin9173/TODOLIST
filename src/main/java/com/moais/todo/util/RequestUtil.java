package com.moais.todo.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
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
        String result = "";
        if(args[0]!=null)  result = args[0].toString();
        return result;
    }


    //Request 에서 파라미터를 추출
    public static String getRequestBody() {
        HttpServletRequest request = getRequest();

        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader br = null;
        String line = "";
        
        try {
            InputStream inputStream = request.getInputStream();
            if(inputStream!=null) {
                br = new BufferedReader(new InputStreamReader(inputStream));
                while((line=br.readLine())!=null) {
                    stringBuilder.append(line);
                }
            } else {
                stringBuilder.append("파라미터 없음");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    //Cookie 에서 값 가져오기
    public static String getCookieParam(String s) {
        HttpServletRequest request = getRequest();
        Cookie[] cookies = request.getCookies();

        String result = null;
        String key;
        String value;

        if(cookies==null) return null;
        for(Cookie c : cookies) {
            key = c.getName();
            value = c.getValue();
            if(key.equals(s)) {
                result = value;
                break;
            }
        }

        return result;
    }


}
