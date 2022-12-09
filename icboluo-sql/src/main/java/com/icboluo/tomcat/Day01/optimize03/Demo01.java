package com.icboluo.tomcat.Day01.optimize03;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import java.io.IOException;

/*
servlet的优化:实现接口的过程中，用到的重写方法只有service，创建一个实现父类，子类extend this
注意;web.xml中class应该选子类，不要选实现类
 */
public class Demo01 extends GenericServlet {
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        servletResponse.getWriter().print("hellogenericservlet");
        System.out.println("hellogenericservlet");
    }
}
