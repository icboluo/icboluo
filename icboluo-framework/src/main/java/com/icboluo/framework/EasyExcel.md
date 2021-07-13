## includeColumnFiledNames

includeColumnFiledNames 对于 ExcelWriter 有时候自定义excel

表头字段会有异常 但是对于 WriteSheet正常，不准写到ExcelWriter

中，只能写到 WriteSheet中，本地postman测试是没有问题的，但是远端

如果写到 ExcelWriter 中会出现空列

## finish

easy excel 执行完成之后必须使用finish

## EasyExcel可以转换成poi使用

## 导入类

excel导入的时候，要注意区分header和body数据，如果用统一的对象接受导入数据，可能存在body和header数据格式不匹配

easy excel 导出需要执行final，否则导出数据失效

##row

row writer handler 是 easy excel 中的行处理器，可以转换为poi

## cell

cell. get cell style 中的风格get出来后，重新设置会覆盖以前的，相当于浅拷贝

而且会影响其他单元格，因为excel中的cell style 只有几种代表

设置单个cell style的时候用clone style from 这个api
