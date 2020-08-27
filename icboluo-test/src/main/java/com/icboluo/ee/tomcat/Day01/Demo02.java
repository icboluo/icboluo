package com.icboluo.ee.tomcat.Day01;

import javax.servlet.*;
import java.io.IOException;

/*
servlet入门：第一步：创建一个普通的java类，实现Servlet接口；
第二步：重写service方法；
第三步：在web.xml中配置Servlet；
	1、配置Servlet的类路径：<servlet>
	2、配置Servlet的映射路径：<servlet-mapping>
 */
/*
servlet对象创建一次，执行init方法，servlet工作接收处理响应数据,关闭servlet方法，调用destory销毁
如何控制对象加载的时间用xml中的number
        number取值小于0或不配置：被访问时才去加载；
        number取值为0时：最后被加载；
        number取值大于0时：数值越小Servlet越优先加载；
 */
public class Demo02 implements Servlet {
    public Demo02() {//为什么他使用类名设置方法的时候能先加载一次y
        System.out.println("servlet的对象被创建了");
    }

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("servlet的类正在初始化");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("servlet接收请求");
        servletResponse.getWriter().print("helloservlet");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("servlet对象的destroy方法被调用了");
    }
}
