使用需要log4j.properties日志
InternalResourceViewResolver：《内部的，资源，..，溶解》视图解析器的一种，ModelAndView..setviewname可配合增加前缀和后缀
@RequestMapping(value="/show3.do" method={RequestMethod.POST，...}，params=”!userId”)可以用？和*，也可以用/{name}/占位符
public ModelAndView test3() {
ModelAndView mv = new ModelAndView();
mv.setViewName("hello");
mv.addObject("msg", "ant 风格的映射*");
return mv;
}
@ResponseStatus(value=HttpStatus.OK)不响应view就相应状态
public void test18(Model model，HttpServletRequest request, HttpServletResponse response, HttpSession session，@RequestParam(value="name")String name) {...接受servlet对象
@CookieValue(value="JSESSIONID",required = false)String jsessionid获取cookie数据
mvc用jstl写就是c:foreach
文件上传解析器CommonsMultipartResolver解决上传中文乱码，和上传文件大小
/：表示绝对路径，指的是localhost:8080/springmvc（项目名称可以省略）
不带/：表示相对路径，相对于当前请求的路径很近很近localhost:8080/springmvc（项目名称可以省略）/hello/show32，相当于show前面全部了
拦截器,spring中和过滤器差不多的东西，但不拦截静态页面
impl...HandlerInterceptor ：preHandle，postHandle，afterCompletion


## 源码

1.在boot项目启动之后---bean初始化之后---执行aware回调的时候---会初始化applicationContext(由ApplicationObjectSupport类承担)，会构建拦截器去List中
2.启动之后当 InitializingBean 子实现 AbstractHandlerMethodMapping.afterPropertiesSet 执行的时候，会调用
AbstractHandlerMethodMapping 会执行detectHandlerMethods 方法，Map.put(RequestMappingInfo(聚合请求路径)，MappingRegistration(映射注册；聚合类名、方法名))
  2在调用之后也会返回去调用1
3.DispatchServlet继承http Servlet，tomcat容器启动的时候会调用servlet的init函数，分发给子类dispatch称为onRefresh函数，onRefresh函数调用多个init函数;
mvc架构读取需要从上到下，上面的部分是接口部分功能，功能单一;例如aware通知接口;InitializingBean 接口
