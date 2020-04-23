package com.sunmj.springcloud.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthFilter extends ZuulFilter {

    /**
     * 过滤类型：pre路由之前
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }
    /**
     * 在zuul 网关李是可以有多个过滤器滴，可以设置过滤的顺序
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 添加是否过滤的条件
     * 这里可以写逻辑判断，是否要过滤，true,永远过滤。
     * @return
     */
    @Override
    public boolean shouldFilter() {
        //在这儿写业务逻辑判断
        //业务逻辑判断，首先要获取到request对象
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        //获取请求的URL
        String requestURI = request.getRequestURI();
        System.out.println(requestURI);
        //放行User的请求
        if(requestURI.startsWith("/user")){
            return false;
        }
        return true;
    }

    /**
     * 过滤的具体逻辑
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        //业务逻辑判断，首先要获取到request对象
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        System.err.println("run"+request.getRequestURI());
        //是否授权
        String token = request.getParameter("token");
        if(StringUtils.isEmpty(token)){
            //false表示不进行路由，不会被zuul转发到后端服务器
            requestContext.setSendZuulResponse(false);
            //设置http响应的状态码：401代表未授权
            requestContext.setResponseStatusCode(401);
            //返回响应信息
            HttpServletResponse response = requestContext.getResponse();
            response.setContentType("application/json;charset=utf-8");
            try {
                response.getWriter().write("{\"code\":401,\"msg:\":\"未授权\"}");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
