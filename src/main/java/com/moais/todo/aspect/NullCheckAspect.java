package com.moais.todo.aspect;

import com.moais.todo.log.ExceptionLog;
import com.moais.todo.vo.ResponseVO;
import com.moais.todo.vo.ResultCode;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@Aspect
@Order(value = 2)
@Component
public class NullCheckAspect {

    @Around("onRequest()")
    public Object nullCheck(ProceedingJoinPoint pjp) throws Throwable {
        try {
            Object[] values = pjp.getArgs();
            if(isParamNull(values[0])) return new ResponseVO(ResultCode.NULL_ERROR, "입력값 누락");
        } catch(Exception e) {
            e.printStackTrace();
            new ExceptionLog(e, "").logging();
        }
        return pjp.proceed();
    }

    public boolean isParamNull(Object vo) throws Exception {
        Field[] fields = vo.getClass().getDeclaredFields();
        Method method = null;

        for(Field f : fields) {
            if(String.valueOf(f.getType()).equals("boolean")) {
                method = vo.getClass().getMethod(f.getName());
            } else {
                method = vo.getClass().getMethod("get" + f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1));
            }
            String value = String.valueOf(method.invoke(vo));
            if(value==null || value.equals("null")) return true;
        }
        return false;
    }

    @Pointcut("execution(public * com.moais.todo.controller.*.*(..))" + "&& !@annotation(com.moais.todo.annotation.Nullable)")
    public void onRequest() {

    }
}
