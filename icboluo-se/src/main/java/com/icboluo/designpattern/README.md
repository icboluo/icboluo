# 设计模式

uml类图：...这个就是类接口继承实现图...

使用设计模式，可以让代码有以下优点

代码重用性：相同的代码，不用多次编写

可读性：编程规范性，便于理解

可扩展性：增加新的功能的时候非常的方便

可靠性：新增功能，对原有功能没有影响

对外呈现一种高内聚，低耦合

依赖：临时用到

关联：一直用到，可以双向，二个平等

## 访问者模式

## 迭代器模式

学校/学院/专业

遍历其中一个，其他的跟着遍历

list里面放list也会遍历出来

## 中介者模式

不要让电器类直接交互，增加中介层，调节关系

## 职责链模式

采购审批流程 由经费确定审批的人是谁

可以由校长-》院长》主任审批，一个审批不了交给下一个

可以设置成环形，就不需要设置开始审批人了，都可以作为开始审批人

## 装饰者

套娃，千层饼

配料继承于装饰者

咖啡继承于咖啡

一起继承于饮料

## 组合模式

一个下面有多个，多个下面又有多个，内涵递归操作

大学、学院、专业继承公共组件

组件提供add、remove、print等方法

客户端使用的时候，用统一的组件即可

## 访问者

歌手评分，双分派





