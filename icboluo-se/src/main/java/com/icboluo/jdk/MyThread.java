public void MyThread{
        // ThreadLocal互不干涉
        // Thread类中有属性，存放多个ThreadLocal对象
        // 内存泄漏是说：单个线程的ThreadLocal资源一直得不到释放；线程死了就不会泄漏了
        // ThreadLocal是一个容器，不能只进不出，时间长了必然会导致内存溢出
        //Thread内存溢出的根源是key没有被清理，而不是弱引用（因为线程被循环使用的，所以会溢出）
        // ThreadLocal会造成数据混乱；每次使用完remove是一个很好的实践（其实一般情况下，数据不会混乱；只是为了实现类似单例的情况下才会出现）
        ThreadLocalMap;
        }
