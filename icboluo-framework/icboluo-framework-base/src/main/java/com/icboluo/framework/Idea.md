## idea 快捷键

* ctrl shift f 全局搜索
* ctrl shift r 全局替换
* shift enter 换行
* fn键 解除锁定
* 单击服务名跳转到启动类中，需要右键下边工具栏边界调整即可

## idea service

如果idea service无法移动相对位置，可能是因为他们颜色不同，删了重新搞一下

service窗口无端口提示、actuator失败：将C:\Users{username}\AppData\Local\Temp\hsperfdata_{username}这个文件删掉就可以了

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

## Git

git soft 是文件变为之前的待提交状态

git远端回滚提交记录

    1.reset返回到这次提交时候的代码，再强制提交
    2.revert将选中的git记录作为要回滚的记录，然后再次commit回滚，再强制提交

#### .gitignore

    不要在idea中隐藏这个文件，往里面动态的添加不需要交给 git管理的东西，gitignore也必须提交
    该文件会把文件里面标注的东西不交给git管理
    .gitignore 项目均需要此文件，避免多于文件上传到远端分支，是一个很好的实践

10054

git config http.sslVerify "false"

git config --global http.sslVerify "false"

443

git config --global --unset http.proxy

git config --global --unset https.proxy

git命令：git add把文件交于git管理（将工作目录的数据放到暂存区域） git commit 把暂存区的内容提交到本地仓库---本地仓库对应的是分布式版本控制系统
git采用的是直接记录快照的方式，而非差异比较（差异比较相当于增量同步，当增量过多的时候，合并比较麻烦）

github 绿色是增加，红色是减少；左边是原来的，右边是新的

## maven

maven settings 文件简介（猜测）

        首先会使用 activeProfiles 去选择 Profiles （例如dev），如果该Profiles里面没有文件，会找mirrors，
        在servers里面可以设置账号密码

## 注释

java注释里面的 <br> 换行 <pre> 再起一个段落
