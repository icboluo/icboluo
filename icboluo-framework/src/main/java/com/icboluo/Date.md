## 格式化

### 前端传输数据到后端

@DateTimeFormat(pattern = "yyyy-MM-dd")

### 后端传输数据到前端

@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")

当设置全局 date format的时候，这个注解对于单个的对象属性并没有什么
作用，用下面的注解可以解决

@JSONField(format ="yyyy-mm-dd HH:mm:ss")