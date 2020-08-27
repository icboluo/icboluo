package com.icboluo.seee.Improve;

/*

    2、将自己知道的方法的调用方式逐一列举出来。
C) 当做参数传递调用  show( method() ); 方法的返回值必须和show方法接收的形参类	型一致。
D) 当做方法的返回值调用 int show(){ return method(); } method()方法的返回值类型必	须和show()的返回值类型一致。
E) 当做三元运算的结果使用。 int max = a > b ? demo1() : demo2(); 方法的返回值类型和	接收结果的类型要一致。
   1、请写出为什么要配置path变量和JAVA_HOME变量
         配置path变量的目的是希望在path变量下路径中的可执行程序在任何目下都可以执行。
         配置JAVA_HOME变量的目的是为了方便查找和更改jdk的目录，防止勿改。
   5、简述java中的关键字、标识符是什么？简述标识符的定义和使用
       标识符的定义规则：
           1、必须由数字、字母、_ 或 $ 组合而成
           2、并且不能是java中的关键字和保留字
           3、也不能以数字开头。
       标识符的定义规范：
           为了方便识别标识符，在定义标识符的时候最好能够做到见名知意。
           标识符在做为类名使用时：首字母要大写，多个单词组合时每个单词的首字母都需要大写。
           标识符在做为变量名使用时：首字母要小写，多个单词组合时，第二个单词开始首字母大写。
   7、简述Java中变量定义和使用注意事项
           1、变量空间中保存的数据类型必须和声明的空间类型一致
           2、变量空间不赋值不能直接使用。
           3、变量空间有自己的使用范围，超出范围无法访问
           4、在同一个范围内，不能存在相同的变量名

   9.java程序是通过jav来实现跨平台性，但jvm不跨平台
jvm	Java Virtual Machinte	虚拟机
jre	Java Runtime Environment	运行环境	jvm+运行时所需的核心类库
jdk	Java Development Kit	开发工具包	jre+开发人员使用的工具
java程序开发步骤	编写 编译 运行	javac.exe 编译器	java.exe 解释器
	编写好java源文件	java源文件经编译器编译成字节码文件	字节码文件经解释器

3、下面关于java中包的说法正确的是(A )b?
A、 在java中可以使用import语句导入包
B、 在java中可以使用package语句导入包
*/

public class Practice_After1_6 {


}
