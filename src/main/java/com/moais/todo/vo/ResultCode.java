package com.moais.todo.vo;

public class ResultCode {
    public static String SUCCESS_CODE = "00"; // 성공
    public static String LOGIN_ERROR = "01";// 인증 에러
    public static String NULL_ERROR = "02";// 요청값 NULL
    public static String DUPLICATE_ERROR = "03"; //중복에러
    public static String TYPE_ERROR = "05"; //타입 에러
    public static String INFO_ERROR = "06"; //정보 불일치

    public static String REFRESH_EXPIRE_ERROR = "07"; //리프레시 토큰 만료
    public static String NONE_ERROR = "08"; //데이터 없음

    public static String ERROR_CODE = "99"; //에러
}
