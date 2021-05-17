```
##定义初始变量
#set($tableName = $tool.append($tableInfo.name, "Mapper"))
##设置回调
$!callback.setFileName($tool.append($tableName, ".java"))
$!callback.setSavePath($tool.append($tableInfo.savePath, "/mapper"))

##拿到主键
#if(!$tableInfo.pkColumn.isEmpty())
    #set($pk = $tableInfo.pkColumn.get(0))
#end

#if($tableInfo.savePackageName)package $!{tableInfo.savePackageName}.#{end}mapper;

import $!{tableInfo.savePackageName}.entity.$!{tableInfo.name};
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * $!{tableInfo.comment}($!{tableInfo.name})表数据库访问层
 *
 * @author $!author
 * @since $!time.currTime()
 */
public interface $!{tableName} {

    /**
     * 通过ID查询单条数据
     *
     * @param $!pk.name 主键
     * @return 实例对象
     */
    $!{tableInfo.name} queryById($!pk.shortType $!pk.name);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param $!tool.firstLowerCase($!{tableInfo.name}) 实例对象
     * @return 对象列表
     */
    List<$!{tableInfo.name}> queryAll($!{tableInfo.name} $!tool.firstLowerCase($!{tableInfo.name}));

    /**
     * 新增数据
     *
     * @param $!tool.firstLowerCase($!{tableInfo.name}) 实例对象
     * @return 影响行数
     */
    int insert($!{tableInfo.name} $!tool.firstLowerCase($!{tableInfo.name}));
    
    /**
     * 新增数据
     *
     * @param $!tool.firstLowerCase($!{tableInfo.name}) 实例对象
     * @return 影响行数
     */
    int insertSelective($!{tableInfo.name} $!tool.firstLowerCase($!{tableInfo.name}));

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<$!{tableInfo.name}> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(List<$!{tableInfo.name}> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<$!{tableInfo.name}> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(List<$!{tableInfo.name}> entities);

    /**
     * 修改数据
     *
     * @param $!tool.firstLowerCase($!{tableInfo.name}) 实例对象
     * @return 影响行数
     */
    int update($!{tableInfo.name} $!tool.firstLowerCase($!{tableInfo.name}));

    /**
     * 通过主键删除数据
     *
     * @param $!pk.name 主键
     * @return 影响行数
     */
    int deleteById($!pk.shortType $!pk.name);

}
```