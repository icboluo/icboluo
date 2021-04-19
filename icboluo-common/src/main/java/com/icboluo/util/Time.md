```
        TimeRecord.start("第一次排序");
        m1(RandomHelper.getRandom(40000));
        TimeRecord.record("第二次排序");
        m2(RandomHelper.getRandom(40000));
        TimeRecord.build();
```

## 第一次排序

> 记录当前的时间和日志，此时time size=1，map：0，'第一次

## 第二次排序

> 打印第一次的执行时间，now-time=time.size-1，/map.get(time.size-1)
> 记录当前的时间和日志，此时time size=2，map：1，'第二次

## 第三次构建

> 打印第二次的执行时间，now-time=time.size-1，/map.get(time.size-1)
> 记录当前的时间和日志，此时time size=3，map：2，'第二次
