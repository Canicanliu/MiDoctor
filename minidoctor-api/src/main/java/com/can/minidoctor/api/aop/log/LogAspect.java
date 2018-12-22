package com.can.minidoctor.api.aop.log;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.can.minidoctor.api.utils.JsonUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ltm
 * @Descripion:
 * @Date: Created in 10:55 2018/12/8
 */
@Component
@Aspect
@Order(1)
public class LogAspect {

	private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);
    private static final String dateFormat = "yyyy-MM-dd HH:mm:ss";
    @Around("@annotation(com.can.minidoctor.api.aop.log.LogParams)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Class<?> targetClass = method.getDeclaringClass();
        StringBuffer classAndMethod = new StringBuffer();

        LogParams methodAnnotation = method.getAnnotation(LogParams.class);
        String[] parameterNames = signature.getParameterNames();
        Object[] args = joinPoint.getArgs();
        Map<String, Object> hashMap = getInput(parameterNames, args);

        if (methodAnnotation != null) {
            classAndMethod.append(methodAnnotation.value());
        }
        String target = targetClass.getName() + "#" + method.getName();
        long start = System.currentTimeMillis();

        logger.info(" 开始调用{}--> {} 参数:{}", classAndMethod.toString(), target, JsonUtils.toJson(hashMap));

        Object result = joinPoint.proceed();
        String jsonFromService = JSONObject.toJSONStringWithDateFormat(result, dateFormat, SerializerFeature.WriteMapNullValue);
        logger.info(" 调用结束{}<-- {}，返回值:{}，耗时{}ms ", classAndMethod.toString(), target, jsonFromService, System.currentTimeMillis() - start);

        return result;
    }
	private Map<String, Object> getInput(String[] parameterNames, Object[] args) {
		Map<String, Object> hashMap = new HashMap();
		if(null==parameterNames){
            return hashMap;
        }
		for(int i = 0; i < parameterNames.length; i++) {
			if(args[i] instanceof HttpServletRequest||args[i] instanceof HttpServletResponse
					||args[i] instanceof MultipartFile){
                continue;
            }
		    hashMap.put(parameterNames[i], args[i]);
		}
		return hashMap;
	}

}
