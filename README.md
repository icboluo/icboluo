### Hi there 👋

<!--
**icboluo/icboluo** is a ✨ _special_ ✨ repository because its `README.md` (this file) appears on your GitHub profile.

Here are some ideas to get you started:

- 🔭 I’m currently working on ...
- 🌱 I’m currently learning ...
- 👯 I’m looking to collaborate on ...
- 🤔 I’m looking for help with ...
- 💬 Ask me about ...
- 📫 How to reach me: ...
- 😄 Pronouns: ...
- ⚡ Fun fact: ...
-->
# 命名规范

## 服务名

这个项目每个服务的spring.application.name和artifactId是一样的

每个服务的stater名称是取消icboluo前缀的大驼峰式，有application后缀


# 技术选型

## 前后端分离+cloud

选择原因

1.不会写前端，不分离以前用mvc写起来恶心\
2.cloud扩展性高

## mvc+spring+mybatis

选择原因

只会用这些

## boot

选择原因

boot是spring一站式解决方案，要用spring，如果和boot搭配会

简化spring的使用难度，简化配置，提供快速启动器

## maven

选择原因

1.虽然看起来啰嗦但是看习惯了，而且好多官网都是maven写的，copy方便\
2.gradle写的不咋好

## nacos

功能：注册中心、配置中心

### 注册中心替代品

cap(consistency强一致性 availability可用性（所有的请求都会收到响应） partition tolerance 分区容错性)

eureka ap ,zookeeper、consul cp，nacos 可ap可cp

eureka：没人更新

eureka自我保护就是不会（立刻）清理死了的服务

eureka已经集成ribbon实现负载均衡算法，ribbon默认的负载均衡算法是轮训，可以修改成其他负载均衡算法或者自定义

ribbon是本地负载均衡，nginx是服务端的负载均衡（集中式的）

除了eureka用自己的@enable...service注解，其他的注册中心都用@EnableDiscoveryClient 

zookeeper：没用过

zookeeper上没有自我保护，一定时间内服务挂掉，会直清理

consul：go写的 

consul是分布式服务发现和配置管理系统


### 配置中心替代品



## 发送请求

### 1.feign

使用方式1：

1.调用方要发送请求，需要提供依赖

2.调用方调用feign service

3.被调用者完成feign调用的接口

使用方式2：

1.被调用者提供client接口

2.调用方引入client依赖和feign依赖

3.调用方用引入的client依赖，或者用service将引入的依赖继承过来

第2种更好一点，被调用者提供被调用服务，第一种方式更简洁

注意：feign的service必须使用mvc注解

### 2. RestTemplate

使用方式：

1.注入到spring容器

2.从spring容器取出来在接口调用出使用


# 项目介绍

## common

包含一些 common和util

公共异常，返回值都在这里面定义

## note

包含note和excel

TODO

分离2个模块

## software

包含java软件环境的安装

其中部分属于.xmind 文件，.md文件图片读不出来，建议在本地打开使用

## test

从hello world到framework

TODO

分离补充为多个模块

## user

提供对外访问的接口

## sql

提供sql的访问方式和sql的一些相关问题

包含：jdbc JdbcTemplate 自定义连接池 c3p0 druid 

TODO

其中tomcat部分未拆分

## nacos-config

配置中心

TODO

并没有在其他服务中使用配置中心的文件

## gateway

网关：路由转发+过滤器

# 项目更新计划

## 更新设计模式内容

缓存：避免数据库访问速度过慢，在数据库上增加了缓存层

## 为什么要使用缓存

    高性能：对于热点数据，把热点数据放到缓存中，用户访问的时候直接去缓存中取，直接操作内存效率高
    高并发：mysql qps 1万（4核8g）,单机redis可以达到10万+，集群更高；把数据库中的部分数据放到
        缓存中，请求不经过数据库，可以提高并发量
    QPS（Query Per Second）：服务器每秒可以执行的查询次数

## 带来的问题：

    复杂度增加：缓存和数据库的一致性、热点缓存等等
    开发成本增加：需要增加缓存服务、需要耗费内存

## 缓存应该包含：
    
    过期时间、淘汰机制、命中率统计等

## 本地缓存：

    jdk自带 hashmap concurrentHashMap 没有过期时间功能
    Ehcache：比较重量，提供嵌入mybatis hibernate作为多级缓存、持久化、集群功能
    Guava Cache：和Spring Cache差不多
    Spring Cache：代码简洁、容易出现缓存穿透，内存溢出
    Caffeine：比Guava Cache各方面性能更加优秀，建议用这个替换
    
## 本地缓存对应的问题：

    本地缓存的优点：低依赖、比较轻量、比较简单相比于分布式缓存
    多个相同服务搭建集群（同一个服务部署到不同的服务器上），各个服务之间的缓存无法共享，
        因为本地缓存只有当前机器上有
    本地缓存的容量受部署的服务器现在明显：服务耗费内存多了，本地缓存的容量就会变少

## 分布式缓存的引入：

    分布式缓存主要解决了单机缓存容量受限制并且无法保存通用的信息
    分布式缓存部署在单独的一台服务器上，一个服务部署再多的服务，也是同一份缓存；并且，单独
        的分布式缓存的性能、容量和提供的功能会更加强大
    分布式缓存缺点：需要引入额外的缓存服务、要保证缓存服务的高可用
    
## 缓存读写模式/更新策略：

### Cache Aside Pattern（旁路缓存模式）:

    write：更新db，删除cache
    read：从cache读数据，读取到返回，读取不到从db读数据放到cache再返回（读取不到就直接返回null）
    适用场景：read比较多的场景
    特点：同时维系db和cache，以db为主
    问题：首次请求一定不在cache中，热点数据可以提前放到缓存中
    
### Read/Write Through Pattern（读写穿透）

    write：查cache，cache中不存在，update db；cache中存在，更新cache，cache服务自己更新db
    read：查cache，cache中读取到，返回；读取不到，从db加载，写到cache，cache返回
    特点：read的时候，上面的是客户端写入cache中，这个是cache服务自己写进cache中的；
        write的时候，也是有cache服务负责cache对db的读写
    问题：和上面的一样
    
### Write Behind Pattern（异步缓存写入）
    
    特点：和上面的差不多，由cache服务负责cache读写，但是上面的是同步方式写入db，这个
        是异步方式批量写入db，写的性能非常高
    适用场景：适用于一些数据经常变化的业务场景，比如点赞数、阅读数量
    问题：对db和cache的一致性有考验，如果cache数据未同步到db，然后cache服务宕机就gg
    
# redis

## redis
    
    redis是c语言开发的数据库，redis的数据是存在内存中的，是内存数据库
    优点：写到内存中，读写速度非常快，广泛应用于缓存方向，也用作分布式锁、消息队列
    Redis 提供了多种数据类型来支持不同的业务场景。Redis 还支持事务 、持久化、Lua 脚本、多种集群方案
    
## redis与Memcached的相同点和不同点

    相同点：基于内存的数据库，一般都用来做缓存使用
        性能非常高
        都有过期策略
    不同点：
    Redis 支持更丰富的数据类型（支持更复杂的应用场景）。Redis 不仅仅支持简单的 k/v 类型的数据，同时还提供 list，set，zset，hash 等数据结构的存储。Memcached 只支持最简单的 k/v 数据类型。
    Redis 支持数据的持久化，可以将内存中的数据保持在磁盘中，重启的时候可以再次加载进行使用,而 Memecache 把数据全部存在内存之中。
    Redis 有灾难恢复机制。 因为可以把缓存中的数据持久化到磁盘上。
    Redis 在服务器内存使用完之后，可以将不用的数据放到磁盘上。但是，Memcached 在服务器内存使用完之后，就会直接报异常。
    Memcached 没有原生的集群模式，需要依靠客户端来实现往集群中分片写入数据；但是 Redis 目前是原生支持 cluster 模式的.
    Memcached 是多线程，非阻塞 IO 复用的网络模型；Redis 使用单线程的多路 IO 复用模型。 （Redis 6.0 引入了多线程 IO ）
    Redis 支持发布订阅模型、Lua 脚本、事务等功能，而 Memcached 不支持。并且，Redis 支持更多的编程语言。
    Memcached过期数据的删除策略只用了惰性删除，而 Redis 同时使用了惰性删除与定期删除。

## redis常用的数据结构
    
### string
    
    普通字符串的基本操作、批量设置、计数器（字符串的内容为整数的时候可以使用）、过期
    使用场景：一般常用在需要计数的场景，比如用户的访问次数、热点文章的点赞转发数量等等
    

数据结构没有readme
合并se中anno和anno


### list

    双向链表 
    应用场景: 发布与订阅或者说消息队列、慢查询
    list实现队列：头部放进去，尾部取出来
    list实现栈：头部放进去，头部取出来
    list可以查看长度，可以分页
    
### hash

    hash 是一个 string 类型的 field 和 value 的映射表，特别适合用于存储对象
    是数组+链表
    应用场景: 系统中对象数据的存储
    
### set
    
    类似于hashset
    可以基于 set 轻易实现交集、并集、差集的操作
    应用场景: 需要存放的数据不能重复以及需要获取多个数据源交集和并集等场景
    你可以将一个用户所有的关注人存在一个集合中，将其所有粉丝存在一个集合。Redis 可以非常方便的实现如共同关注、
        共同粉丝、共同喜好等功能。这个过程也就是求交集的过程

### sorted set

    和 set 相比，sorted set 增加了一个权重参数 score，使得集合中的元素能够按 score 进行有序排列
    应用场景： 需要对数据根据某个权重进行排序的场景。比如在直播系统中，实时排行信息包含直播间在线用户列表，
        各种礼物排行榜，弹幕消息（可以理解为按消息维度的消息排行榜）等信息
        
## redis单线程-多线程

## redis给缓存数据设置过期时间

    可以设置过期时间，可以查看过期时间
    作用：防止内存溢出、部分业务场景，比如验证码1分钟内有效、用户登录的token一天之内有效
    
### redis如何判断是否过期
    
    redis通过一个过期字典，保存过期时间，可以看做一个hash表，hash表的键是指向redis数据库中
        的键，过期字典的值保存redis数据库键的过期时间，是一个long long类型的整数

### redis过期数据的删除策略

常用的过期数据删除策略：

#### 惰性删除

只会在取出key的时候才对数据进行过期检查。这样对CPU最友好，但是可能会造成太多过期 key 没有被删除
    
#### 定期删除

每隔一段时间抽取一批 key 执行删除过期key操作。并且，Redis 底层会并通过限制删除操作执行的时长和频率来减少删除操作对CPU时间的影响，对内存更加友好

redis采用的是  定期删除+惰性/懒汉式删除（先抽取一批删，在访问的时候再看看是否过期，过期了删）
    
问题：这两种方式都是随机删除，还是删不干净，需要引入

### redis内存淘汰机制

volatile-lru（least recently used）不稳定的（这里的意思是过期的） 最少的 最近的 使用

    从已设置过期时间的数据集中挑选最近最少使用的数据淘汰（这个是最近的）
    
volatile-ttl：

    从已设置过期时间的数据集中挑选将要过期的数据淘汰
    
volatile-random：

    从已设置过期时间的数据集中任意选择数据淘汰
    
allkeys-lru（least recently used）：

    当内存不足以容纳新写入数据时，在键空间中，移除最近最少使用的 key（这个是最常用的）
    
allkeys-random：

    从数据集中任意选择数据淘汰
    
no-eviction：驱逐
    
    禁止驱逐数据，也就是说当内存不足以容纳新写入数据时，新写入操作会报错
        
volatile-lfu（least frequently used）：频繁的

    从已设置过期时间的数据集中挑选最不经常使用的数据淘汰（这个是总的）
    
allkeys-lfu（least frequently used）：
    
    当内存不足以容纳新写入数据时，在键空间中，移除最不经常使用的 key
    
## redis持久化机制

### 快照（snapshotting）持久化（RDB）

    是redis默认的持久化方式，可以通过创建快照来获得存储在内存里面的数据在某个时间点上的副本，
    redis创建快照后，可以对快照进行备份，可以将快照复制到其他服务器，创建具有相同数据的服务器副本，
    （这时我们可以用redis主从结构，提高redis性能）；也可以将快照保留到原地，以便重启服务器的时候使用
    save 900 1    在900秒(15分钟)之后，如果至少有1个key发生变化，Redis就会自动触发BGSAVE命令创建快照。
    save 300 10   在300秒(5分钟)之后，如果至少有10个key发生变化，Redis就会自动触发BGSAVE命令创建快照。
    save 60 10000 在60秒(1分钟)之后，如果至少有10000个key发生变化，Redis就会自动触发BGSAVE命令创建快照。

### AOF（append-only file）持久化 （附加

    redis配置文件中3种不同的持久化方式
    appendfsync always    #每次有数据修改发生时都会写入AOF文件,这样会严重降低Redis的速度
    appendfsync everysec  #每秒钟同步一次，redis性能基本没有什么影响，显示地将多个写命令同步到硬盘
    appendfsync no        #让操作系统决定何时进行同步






