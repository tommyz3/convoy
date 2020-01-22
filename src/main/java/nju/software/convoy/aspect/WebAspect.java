package nju.software.convoy.aspect;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import nju.software.convoy.controller.ResponseBody.Result;
import nju.software.convoy.controller.ResponseBody.ResultFactory;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @Author: tommy_z
 * @Date: 2020/1/16
 */
@Slf4j
@Aspect
@Component
public class WebAspect {
    @Pointcut("execution(public * nju.software.convoy.controller.*.*(..))")
    public void web(){}

    /**
     * 记录请求内容&&参数校验
     * @param pjp
     * @param bindingResult
     * @return
     * @throws Throwable
     */
    @Around("execution(public * nju.software.convoy.controller.*.*(..)) && args(..,bindingResult)")
    public Object doAround(ProceedingJoinPoint pjp, BindingResult bindingResult) throws Throwable {
        // 接收到请求，记录请求内容
        long startTime = System.currentTimeMillis();
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //请求参数
        String requestParam = getParams(pjp, request);
        //请求执行的类路径
        String classPath = signature.getDeclaringTypeName();
        //请求执行的方法名
        String methodName = signature.getName();
        log.info("请求:" + classPath + "#" + methodName + "\t参数:" + requestParam);
        if (bindingResult.hasErrors()){
            log.warn("请求参数错误：" + bindingResult.getFieldError());
            return ResultFactory.failed(bindingResult.getFieldError().getDefaultMessage());
        }
        return pjp.proceed();
    }

    /**
     * 记录每个请求的返回值
     * @param ret
     */
    @AfterReturning(returning = "ret", pointcut = "web()")
    public void doAfterReturning(Result ret){
        log.info("返回值: " + ret.toString());
    }

    /**
     * 获取请求参数列表
     * @param joinPoint
     * @param request
     * @return
     * @throws Exception
     */
    public String getParams(JoinPoint joinPoint, HttpServletRequest request) throws Exception {
        String method = request.getMethod();
        String queryString = request.getQueryString();
        String params = "";
        Object[] args = joinPoint.getArgs();
        if (args.length > 0) {
            if ("POST".equals(method)) {
                Object object = args[0];
                //过滤掉 ServletRequest 和 ServletResponse 类型的参数
                Object paramObject = Arrays.stream(args).filter(t -> !(t instanceof ServletRequest) && !(t instanceof ServletResponse)).collect(Collectors.toList());
                params = JSONObject.toJSONString(object);
            } else if ("GET".equals(method)) {
                params = queryString;
            }
            params = URLDecoder.decode(params, "utf-8");
        }
        return params;
    }

}
