## Springmvc:Model View Controller 模型视图控制器

## 名词/注解

Model:封装应用的状态，并实现应用的功能

View:提供界面来与用户进行人机交互

@Controller:控制应用程序的流程和处理用户所发出的请求

@ResponseBody 表示该方法的返回结果直接写入HTTP response body，

@ResponseBody	是把Controller方法返回值转化为JSON，称为序列化

@RestController，将该注解使用在Controller类上，所有方法都默认是响应json格式的数据了

@RequestBody	是把接收到的JSON数据转化为Pojo对象，称为反序列化

@RequestBody用于获取请求体内容。直接使用得到是key=value&key=value...结构的数据。 一般用于接收一个json数据。

RequestMapping 提供路由信息，负责URL到Controller中的具体函数的映射

@RequestParam@PathVariable@GetMapping@PostMapping@PutMapping@DeleteMapping

@SpringBootApplication 申明让spring boot自动给程序进行必要的配置@Configuration ，@EnableAutoConfiguration 和 @ComponentScan 三个配置。

ConfigurationProperties 声明当前类为属性读取类

Propertysource 指定外部属性文件

@Value 属性注入,声明在属性（变量）上



