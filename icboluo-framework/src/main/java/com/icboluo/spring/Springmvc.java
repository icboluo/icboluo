package com.icboluo.spring;
/*
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

 */
public class Springmvc {
}
