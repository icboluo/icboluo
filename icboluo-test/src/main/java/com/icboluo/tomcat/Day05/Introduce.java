package com.icboluo.tomcat.Day05;
/*
jsp:Java Server Pages可以写java&html
第一次访问JSP页面： JSP页面被tomcat翻译成Servlet

jspService方法：
jsp中的html：out.print（"<html>"）
jsp中的java部分：原样输出

jsp中写java：
脚本片段<%%>
脚本表达式：<%=%>代替response.getWriter().print();
EL:expression language,${}代替脚本表达式，主要取值(从域对象)并显示
jsp的域对象：page、reques、session、servletContext
ctrl alt shift u 框架图
jstl： the javaserver pages standard tag library 标准标签库
用标签封装了一些通用的业务逻辑
c:if,c:foreach就是这里面的
 */
public class Introduce {
}
