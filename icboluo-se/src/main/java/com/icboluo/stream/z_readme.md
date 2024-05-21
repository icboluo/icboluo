## stream

all match 如果流为空返回true，any match则返回false

分区如果用null值，继续后序操作，会npe（分区null情况需要重点关注

分区功能是可能存在只有一个true的情况的，如果map.get(false).size会造成NPE，在分区链式调用的时候要先检查是否存在key

Steam.peek方法属于中间操作，必须拥有终止操作才会执行，否则idea会提示;sonarlint提示可以不管

Stream andThen 方法大部分可以省略一部分
