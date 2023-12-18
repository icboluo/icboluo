-- hset
hset test:student1 id 110
hset test:student1 age 18
hset test:student1 name zhangsan
hgetall test:student1
hget test:student1 sex

hmset test:student2 id 14 age 19 name tim
hmget test:student2 id name age
hgetall test:student2

-- 只能给整数增加整数值，2者缺一不可
hincrby test:student1 age 1
hget test:student1 age
hincrbyfloat  test:student1 age 1.1
hget test:student1 age
-- 如果没有值，从0开始，下一次加了就是1
hincrby  test:student1 num 1
hgetall test:student1
hdel test:student1 num
-- 当key不存在，（hash是双层key)的时候可以设置成功，否则设置失败
hsetnx test:student1 name fail
hsetnx test:student1 sex male
hgetall test:student1
hdel test:student1 sex
hexists test:student1 sex
-- 所有的key、所有的val、entry的个数
hkeys test:student1
hvals test:student1
hlen test:student1
-- l 使用场景：栈、队列、阻塞式消息队列、动态有限集合
lpush test:list1 a b c
lrange test:list1 0 -1
rpush test:list1 d e f
lrange test:list1 0 -1
llen test:list1
lindex test:list1 3
lset test:list1 3 z
lrange test:list1 0 4
lrange test:list1 -2 -1
-- 在元素的前面和后面加元素
linsert test:list1 before a i
linsert test:list1 after  a j
lrange  test:list1 0 -1
lpop test:list1
-- 移除多少个a元素
lrem test:list1 0 a
-- subList
ltrim test:list1 0 4
lrange test:list1 0 -1

