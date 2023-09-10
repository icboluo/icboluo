//package com.icboluo.spring.accountservlet;
//
//
//import java.io.IOException;
//
//
//public class HelloServlet extends HttpServlet {
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        doGet(request, response);
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//      //ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
//        WebApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
//        IHelloService helloService = (IHelloService) ac.getBean("helloService");
//        helloService.sayHello();
//    }
//}
