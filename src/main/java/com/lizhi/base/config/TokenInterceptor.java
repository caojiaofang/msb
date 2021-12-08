package com.lizhi.base.config;

import com.alibaba.fastjson.JSONObject;
import com.lizhi.utils.cache.MemoryCacheUtil;
import com.lizhi.utils.token.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: lizhi
 * @Description:
 * @Date: create in 2020/6/23 17:13
 */
@Slf4j
//@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /*判断请求方法*/
        if (request.getMethod().equals("OPTIONS")){
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }
        response.setCharacterEncoding("utf-8");
        String token = request.getHeader("token");
        String userAcct = request.getHeader("userAcct");
        String tokenBack = MemoryCacheUtil.get(userAcct);
        if (token != null && tokenBack != null && token.equals(tokenBack)){
            boolean result = TokenUtil.verify(token);
            if (result){
                /*log.info("通过拦截器");*/
                return true;
            }
        }
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        try{
            JSONObject json = new JSONObject();
            json.put("msg","token verify fail");
            json.put("code","50000");
            response.getWriter().append(json.toJSONString());
        }catch (Exception e){
            e.printStackTrace();
            response.sendError(500);
            return false;
        }
        return false;
    }
}
