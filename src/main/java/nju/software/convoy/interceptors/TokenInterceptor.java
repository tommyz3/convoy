package nju.software.convoy.interceptors;

import com.alibaba.fastjson.JSONObject;
import nju.software.convoy.controller.ResponseBody.Result;
import nju.software.convoy.controller.ResponseBody.ResultFactory;
import nju.software.convoy.util.JwtUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器类
 * @Author: tommy_z
 * @Date: 2020/1/16
 */
@Component
public class TokenInterceptor implements HandlerInterceptor {
    /**
     * 对请求的header中的token进行判断
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setCharacterEncoding("utf-8");
        String token = request.getHeader("accessToken");
        if (null != token){
            boolean result = JwtUtil.verify(token);
            if (result)
                return true;
        }
        Result r = ResultFactory.authFailed();
        response.getWriter().write(JSONObject.toJSONString(r));
        return false;
    }
}
