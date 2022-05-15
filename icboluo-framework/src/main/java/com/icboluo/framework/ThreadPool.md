## Executor

通过 Executor 来启动线程比使用 Thread 的 start 方法更好，除了更易管理，效率更好（用线程池实现，节约开销）外，还有关键的一点：有助于避免 this 逃逸问题。

创建：实现 Runnable或Callable [ThreadPool](ThreadPool.java)

执行：可以使用ThreadPoolExecutor或者ScheduledThreadPoolExecutor执行

计算结果：Future

