package com.icboluo.tomcat.Day07;


import javax.servlet.*;
import java.io.IOException;

/*
filter过滤器是一个能够拦截客户端与服务端请求与相应的web组件（servlet、filter、listener）
filter过滤器是链式的
tomcat启动会立即加载filter过滤器，servlet是延时加载
filter需要web.xml配置，url：配置拦截的资源路径
web/*拦截web下所有，/*拦截html、jsp、servlet所有
*.xxx后面有xxx都能拦截
 */
public class Introduce implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("拦截请求");
        //放行
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("拦截响应");
    }

    @Override
    public void destroy() {

    }
}
