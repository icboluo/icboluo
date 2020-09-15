package com.icboluo;
/**
 * @author lp
 */ /*
Springmvc:Model View Controller 模型视图控制器
Model:封装应用的状态，并实现应用的功能
View:提供界面来与用户进行人机交互
Controller:控制应用程序的流程和处理用户所发出的请求
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
@RequestParam(value=””, required=true/false, defaultValue=””)
@RequestParam比之@PathVariable的区别是不需要在注解中使用占位符{xxx}
@GetMapping：相当于@RequestMapping（method = RequestMethod.GET）
@PostMapping@PutMapping@DeleteMapping
@CookieValue(value="JSESSIONID",required = false)String jsessionid获取cookie数据
mvc用jstl写就是c:foreach
@ResponseBody	是把Controller方法返回值转化为JSON，称为序列化
@RestController，将该注解使用在Controller类上，所有方法都默认是响应json格式的数据了。
@RequestBody	是把接收到的JSON数据转化为Pojo对象，称为反序列化
@RequestBody用于获取请求体内容。直接使用得到是key=value&key=value...结构的数据。 一般用于接收一个json数据。
文件上传解析器CommonsMultipartResolver解决上传中文乱码，和上传文件大小
/：表示绝对路径，指的是localhost:8080/springmvc（项目名称可以省略）
不带/：表示相对路径，相对于当前请求的路径很近很近localhost:8080/springmvc（项目名称可以省略）/hello/show32，相当于show前面全部了
拦截器,spring中和过滤器差不多的东西，但不拦截静态页面
impl...HandlerInterceptor ：preHandle，postHandle，afterCompletion

一个请求匹配前端控制器 DispatcherServlet 的请求映射路径(在 web.xml中指定), WEB 容器将该请求转交给 DispatcherServlet 处理
DispatcherServlet 接收到请求后, 将根据 请求信息 交给 处理器映射器 （HandlerMapping）
HandlerMapping 根据用户的url请求 查找匹配该url的 Handler，并返回一个执行链
DispatcherServlet 再请求 处理器适配器(HandlerAdapter) 调用相应的 Handler 进行处理并返回 ModelAndView 给 DispatcherServlet
DispatcherServlet 将 ModelAndView 请求 ViewReslover（视图解析器）解析，返回具体 View
DispatcherServlet 对 View 进行渲染视图（即将模型数据填充至视图中）
DispatcherServlet 将页面响应给用户
 */
public class Springmvc {
}
