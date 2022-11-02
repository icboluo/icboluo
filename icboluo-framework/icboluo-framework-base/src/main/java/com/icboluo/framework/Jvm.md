## python

python 是一个强类型、动态、解释性语音;

强类型：a定义为list之后不能改为int;

动态：不需要写代码的时候先确认数据类型，运行期自行确认（运行容易有bug

解释性：不需要编译，可以直接运行

## JDK

可以简单理解jvm为bin包、jre包含了bin+lib包、jdk包含jre+exe命令包

jvm Java Virtual Machinte 虚拟机

jre Java Runtime Environment 运行环境 jvm+运行时所需的核心类库

jdk Java Development Kit 开发工具包 jre+开发人员使用的工具

java程序开发步骤 编写 编译 运行 javac.exe 编译器 java.exe 解释器

编写好java源文件 java源文件经编译器编译成字节码文件 字节码文件经解释器


JDK 和 JRE 有什么区别？

JDK：Java Development Kit 的简称，java 开发工具包，提供了 java 的开发环境和运行环境。

JRE：Java Runtime Environment 的简称，java 运行环境，为 java 的运行提供了所需环境。

具体来说 JDK 其实包含了 JRE，同时还包含了编译 java 源码的编译器 javac，还包含了很多 java 程序调试和分析的工具。简单来说：如果你需要运行 java 程序，只需安装 JRE 就可以了，如果你需要编写 java 程序，需要安装 JDK。

## JAVA_HOME

为什么要配置path变量和JAVA_HOME变量

配置path变量的目的是希望在path变量下路径中的可执行程序在任何目下都可以执行。

配置JAVA_HOME变量的目的是为了方便查找和更改jdk的目录，防止勿改。

## 程序计数器

生命周期和线程一样

