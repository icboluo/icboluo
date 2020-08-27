#excel和mysql相互转换的工具
>依赖alibaba EasyExcel
##可以初步使用了
#####还是有很多bug需要更改，以后遇到不能识别的再说吧
##1.excel导成建表语句
###导入模板不需要固定，是根据第二行的列名来取值的
###excel导入模板
####前3个字段为必填，没有处理不填的情况
| 列名 |  字段类型 |  长度  | 是否为空  |  默认值 |  备注 |
| ---- |   ---- |----|----|----|----|
| id   |       char  |   36   | NO | null| 主键 |
|      |          |      |  |
|      |          |      |  |
##2.下载excel模板
### 用java代码直接生成excel
##3.mysql生成数据库文档
https://github.com/alibaba/easyexcel/blob/master/docs/API.md



