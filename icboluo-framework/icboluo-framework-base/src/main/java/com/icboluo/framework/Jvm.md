## python

python 是一个强类型、动态、解释性语音;

强类型：a定义为list之后不能改为int;

动态：不需要写代码的时候先确认数据类型，运行期自行确认（运行容易有bug

解释性：不需要编译，可以直接运行

## JDK简述

可以简单理解jvm为bin包、jre包含了bin+lib包、jdk包含jre+exe命令包

jvm Java Virtual Machine 虚拟机

jre Java Runtime Environment 运行环境 jvm+运行时所需的核心类库

jdk Java Development Kit 开发工具包 jre+开发人员使用的工具

java程序开发步骤 编写 编译 运行 javac.exe 编译器 java.exe 解释器

编写好java源文件 java源文件经编译器编译成字节码文件 字节码文件经解释器

JDK 和 JRE 有什么区别？

JDK：Java Development Kit 的简称，java 开发工具包，提供了 java 的开发环境和运行环境。

JRE：Java Runtime Environment 的简称，java 运行环境，为 java 的运行提供了所需环境。

具体来说 JDK 其实包含了 JRE，同时还包含了编译 java 源码的编译器 javac，还包含了很多 java 程序调试和分析的工具。简单来说：如果你需要运行
java 程序，只需安装 JRE 就可以了，如果你需要编写 java 程序，需要安装 JDK。

## JAVA_HOME

为什么要配置path变量和JAVA_HOME变量

配置path变量的目的是希望在path变量下路径中的可执行程序在任何目下都可以执行。

配置JAVA_HOME变量的目的是为了方便查找和更改jdk的目录，防止勿改。

## 程序计数器

生命周期和线程一样

## jdk

jmm（java内存模型：为了屏蔽系统和硬件的差异，避免一套代码在不同平台效果不一致的情况

cpu高速缓存：cpu内部的缓存，存储最近使用的数据，读取速度快 (解决cpu处理速度和内存处理速度不对等：处理逻辑和redis类似)

## java 中的5种引用方式

Strong Reference

最常见的引用类型，只要强引用存在，垃圾收集器就永远不会回收被引用的对象

Soft Reference

内存充足时不会被回收，内存不足时会被回收

可以使用软引用实现缓存，该缓存属于内存敏感型的；单独的作为缓存服务可以使用，一般项目中应该没有使用场景

```java
public class aCache<K, V> {
    ConcurrentHashMap<K, SoftReference<V>> cache;

    ReferenceQueue<V> queue;

    /**
     * 清理已被GC回收的对象
     * 在put、get、size等方法调用第一行可以先调用，减少书写复杂度
     */
    private void processQueue() {
        Reference<? extends V> ref;
        while ((ref = queue.poll()) != null) {
            cache.entrySet().removeIf(entry -> entry.getValue() == ref);
        }
        // put需修改： cache.put(key, new SoftReference<>(value, queue));
    }

    public V get(K key) {
        var softRef = cache.get(k);
        if (softRef == null) {
            return null;
        }
        V value = softRef.get();
        if (value == null) {
            // 如果软引用已被回收，则移除该项
            // 也可以去数据库再查出来放到cache中，按实际情况选择
            cache.remove(key);
        }
        return value;
    }
}
```

Weak Reference

比软引用更弱，只要发生垃圾回收，弱引用对象就会被回收。常用于避免内存泄漏

Phantom Reference(虚引用)

Final Reference(终结器引用)
