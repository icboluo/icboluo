## idea 快捷键

* ctrl shift f 全局搜索
* ctrl shift r 全局替换
* shift enter 换行
* fn键 解除锁定
* 单击服务名跳转到启动类中，需要右键下边工具栏边界调整即可

## idea service

如果idea service无法移动相对位置，可能是因为他们颜色不同，删了重新搞一下

## .bak

文件加 .bak后缀可以使文件失效

## spelling

可以将非词典单词加入到spelling中，在settings里面可以搜索整理

## 图标

idea的重写接口图标是不一致的，仔细看，接口重写具体类型接口表现的是O，接口重写泛型接口表现的是I

## idea链接mysql

1.通过联网下载驱动

2.找到本地mysql的jar包，配置驱动就可以了

ctrl shift r 全局搜索 可以搜索数据库中的表的使用

## class]

idea 出现class] 的现象是因为pom文件的问题，新版本的Spring就有这样的问题，但是不太影响使用，和bean本身关系不大
bean name还是正确的，只是展示有问题，和toString 关系也不大

## 插件

代码量统计插件

statistic lines 包含了空行和注释，line code 仅包含代码，注释和空行不包括

## debug

idea 打断点卡住一个线程，其他的线程似乎也会被卡住，由当前线程分发的异步线程也就不动了，在Thread.sleep中
