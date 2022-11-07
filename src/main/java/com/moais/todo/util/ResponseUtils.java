package com.moais.todo.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class ResponseUtils {

    public static void tokenCookie(String token, String refresh_token, HttpServletResponse response) {
        tokenCookie(token, response);
        Cookie refresh_token_cookie = new Cookie("refresh_token", refresh_token);
        refresh_token_cookie.setMaxAge(1000 * 60 * 60 * 24);
        refresh_token_cookie.setPath("/");
        refresh_token_cookie.setHttpOnly(true);
        response.addCookie(refresh_token_cookie);
    }

    public static void tokenCookie(String token, HttpServletResponse response) {
        Cookie token_cookie = new Cookie("token", token);
        token_cookie.setMaxAge(1000 * 60 * 10);
        token_cookie.setPath("/");
        token_cookie.setHttpOnly(true);
        response.addCookie(token_cookie);
    }
}
