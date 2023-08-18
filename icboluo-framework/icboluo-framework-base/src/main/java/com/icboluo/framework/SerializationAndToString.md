# idea的debug提示属于toString提示

# 数据和外部交互的时候需要序列化和反序列化

# 序列化和toString没有什么关系

alibaba fastjson 序列化成字节数据的时候需要增加get方法，否则序列化不出来

# get方法jackson是可以序列化的，防止序列化可以在方法上加@Transient注解

fastjson需要放到序列化工具中的第一个，要不然都被别人执行了

# jackson

jackson序列化使用@JsonProperty即可

@JsonFormat jackson序列化使用这个

## 父类序列化

当一个父类实现了序列化，它的子类也自动实现序列化，不用显示进行实现了

toString几乎相反，但又完全不一样

## 异常

实体类里面如果一个字段都没有，可能导致序列化异常，这个情况是测试的时候容易发生的

## 日期

前端传输数据到后端 DateTimeFormat(pattern = "yyyy-MM-dd")

后端传输数据到前端 JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")

当设置全局 date format的时候，这个注解对于单个的对象属性并没有什么 作用，用下面的注解可以解决

JSONField(format ="yyyy-mm-dd HH:mm:ss")
