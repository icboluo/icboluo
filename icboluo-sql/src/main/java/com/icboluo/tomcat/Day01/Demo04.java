package com.icboluo.tomcat.Day01;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/*用一个html输出到网页实现：class extends httpservlet,,class经过web.xml生产url，
html加载url产生网页，输入html就是输入url
网页中输入...html
xml中servlet作用：由路径生成一个name，
servlet-mapping：由name生成一个url
1. 完全匹配          /user/hello          资源路径为/user/hello时可以访问
2. 目录匹配         /user/*              资源路径中含有/user目录均可访问
3. 后缀名匹配        *.do                 资源路径中以.do结尾的均可访问
4. 缺省路径         /                    访问的路径找不到，就会去找缺省路径
 */
public class Demo04 extends HttpServlet {
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        servletResponse.getWriter().print("hellohttpservlet");
        System.out.println("hellohttpservlet");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("hello doget method");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("hello dopost method");
    }
}
