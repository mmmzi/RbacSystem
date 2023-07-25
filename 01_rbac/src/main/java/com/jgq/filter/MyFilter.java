package com.jgq.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * 自定义的过滤器
 */
@WebFilter("/*")
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    /**
     * 过滤器拦截方法
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //将请求和响应对象完成强转
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //获取此次的请求地址
        String requestURI = request.getRequestURI();
        System.out.println("请求地址为：" + requestURI);
        //session的校验
        //放行登录功能及静态资源
        if (requestURI.equals("/login.jsp") || requestURI.equals("/userLogin") || requestURI.startsWith("/static")) {
            //放行
            filterChain.doFilter(request, response);
            return;
        }
        //完成session的校验
        //获取session对象
        HttpSession session = request.getSession();
        //获取sessin资源
        Object user = session.getAttribute("user");
        //pand
        if (user != null) {
            //放行不需要进行url地址权限校验的请求
            if (requestURI.equals("/main.jsp") || requestURI.equals("/menuInfo")) {
                //放行
                filterChain.doFilter(request, response);
                return;
            }
            //校验url地址信息
            //获取用户url地址信息
            List<String> urls = (List<String>) session.getAttribute("urls");
            //校验
            for (String url : urls) {
                if (requestURI.equals(url)) {
                    //放行
                    filterChain.doFilter(request, response);
                    return;
                }
            }
            //拦截请求，响应权限不足
            response.getWriter().write("权限不足");
            return;
        }
        //跳转登录页面
        response.sendRedirect("/login.jsp");
        return;
    }

    @Override
    public void destroy() {
    }
}
