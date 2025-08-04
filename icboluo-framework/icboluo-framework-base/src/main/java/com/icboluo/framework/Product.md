## 产品生命周期

GA->EOM->EOP->EOFS->EOS

GA(general availability 一般可获得性) 发布、通用可获得性、产品上市时间

EOM(end of marketing 营销) 停止销售时间

EOP(end of production) 停止生产

EOFS(end of full support) 停止全面支持，对版本新发现的缺陷停止修复，不再提供新的补丁版本，已发现的缺陷将继续进行根因分析和修复

EOS(end of service and support) 停止服务时间

## 开发流程

uat环境发布后，禁止大面积修改代码，问题回归后，没有保险不能随便修改

版本迭代过程中，前期多修改代码，后期少修改代码

## 系统优化方向

1.最长耗时

降低效果最明显，例如优化sql，多线程编程

2.最多使用

整体效果最好，例如鉴权、系统类公告

## 调接口

调用第三方接口的时候，需要增加日志记录，必要时，可以try catch，error信息可以入库

## init

init异步处理的时候的缺点

单个接口调用时间难以统计，难以确定那个接口耗时多，难以优化

debug调试困难，一跳过断点就好多接口通过，打印好多日志

代码复杂度上升，本地线程的数据需要同步到异步线程才能使用

## 异常

业务异常情况的处理，可以从2个角度阐述：

* 抛出异常，扩大异常，使异常在开发阶段更加容易暴露出来
* 隐藏异常，对异常进行额外处理，可以对各种数据做兼容性支持，不容易报错，但是错误数据流转，会造成回溯异常困难

## 业务

* 对于状态字段，从业务数据里面提取所有的状态有些不合理，应该从配置表中获取

## 对于web层开放的接口

对于流程数据来说，将实体参数分散开来比较合适，这样的好处有：

* 更容易生成接口文档，对外接口更加简单
* 对于后端来说更清晰，不会出现一个接口混乱的情况，业务清晰
* 校验更加简单，可以统一处理校验器

## 状态值

带有状态值的情况下，单据的新增修改处理需要首先判断状态值，防止页面打开多个，多次调用对数据库造成影响

对参数要进行合法性校验

## sort

在进行复杂比较排序的时候，可以使用权重，这个是目前为止最好的方式

比较器中有then函数，可以代替多级比较器

java用多个条件排序可以使用权重，是目前为止最好的方式

## 约束

链式调用不如分发式调用，从数据库找130，再从枚举中找到匹配的枚举（好像在枚举上直接标记130的数据更加简单，可以省略一个映射关系

把配置项写到java代码中稍显不足，每建一个表头，就要新建一个i18的枚举类与之对应，而且需要维护默认值、个人适配的值

## 保存

对于保存的数据和保存状态要进行严格的区分，可以增加draft来区别2种；

保存的数据如果是新建一张表，不需要增加saveStatus来标识来源于保存的数据

## isAdmin

```java
class a {
    // 这种写法更好一点
    boolean method1() {
        QueryWrapper<DB> qw = new QueryWrapper<>();
        qw.eq("user_id", webcontext.userId());
        // equals判断交于mysql，减少服务器端再次校验（此块并不能说是优点，mysql是大小写不敏感的，其他的sql校验可能需要写函数；
        // 相反校验写到服务器端更加统一，会忽略sql的差异性
        qe.eq("role", "admin");
        DB userRole = mapper.selectOne(qw);
        return userRole != null;
    }

    boolean method2() {
        QueryWrapper<DB> qw = new QueryWrapper<>();
        qw.eq("user_id", webcontext.userId());
        DB userRole = mapper.selectOne(qw);
        return userRole != null && "admin".equalsIgnoreCase(userRole.getRole());
    }
}
```

## HttpServletRequest

HttpServletRequest 可以注入，也可以在Controller方法内获取，向后传参，如果下层需要使用，注入好一点

```java
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

public class Http {
    // 注入获取
    @Resource
    private HttpServletRequest httpServletRequest;

    // Controller方法内获取，下层有需要，向后传参即可
    @GetMapping
    public void init(HttpServletRequest request) {

    }
}
```
