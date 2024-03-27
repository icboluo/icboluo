# idea的debug提示属于toString提示

# 序列化和toString没有什么关系

    反序列化是别的变为java，序列化是java变为别的
    数据和外部交互的时候需要序列化和反序列化
    序列化和java中的修饰符无关，例如 private final

alibaba fastjson 序列化成字节数据的时候需要增加get方法，否则序列化不出来

# get方法jackson是可以序列化的，防止序列化可以在方法上加@Transient注解

fastjson需要放到序列化工具中的第一个，要不然都被别人执行了

## 父类序列化

当一个父类实现了序列化，它的子类也自动实现序列化，不用显示进行实现了

toString几乎相反，但又完全不一样

java的序列化id是做版本一致性判断的，如果都设置成1，则多个类之间可以相互序列化；jdk本身的序列化有一些问题，其他多种框架支持高效或者更安全的序列化

## 异常

实体类里面如果一个字段都没有，可能导致序列化异常，这个情况是测试的时候容易发生的

## 日期 fastjson

JSONField(format ="yyyy-mm-dd HH:mm:ss")

## @Transient

@Transient注解在接口上也是有效果的，而且作为父类，加上此注解，子类重写的方法也不会被序列化

需要导入jdk的包，使用lombok的时候，放在字段上不生效，需要放到get方法上

@Transient 在 spring.data包中的作用主要是 hibernate里面，在其他地方，例如 mybatisPlus是没有效果的

在 SpringBoot自动转换为JSON字符串时，使用的是Jackson, 而Jackson会利用java的反射机制来获取对象的所有属性

(包括 @Transient标记的属性), 再将他们转换为json格式的字符串;

所以在字段上加 Transient关键字是没有效果，但是在get方法上加 @Transient注解有效果

mybatisPlus 没有绕过 Transient的检查，相对友好，但是mp又不支持注解

也就是说mp支持的是字段上加关键字，不支持注解

要想mp和jpa都起作用，需要在字段上和get方法上都加声明

有一个 javax包里面的注解暂未调查
