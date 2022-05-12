package com.icboluo.controller;

import com.icboluo.annotation.ResponseResult;
import com.icboluo.service.AsyncService;
import com.icboluo.util.IcBoLuoException;
import com.icboluo.util.ThreadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

/**
 * <p/>异步执行机制首先开启异步
 * <p/>在需要异步执行的类或方法上开启异步功能
 * <p/>同类直接调用异步功能无效
 * <p/>异步处理流程，异步返回值不影响主流程，只是一个额外的处理
 *
 * @author icboluo
 * @since 2021-27-19 21:27
 */
@RestController
@RequestMapping("/async")
@Slf4j
@ResponseResult
public class AsyncController {

    @Resource
    private AsyncService asyncService;

    /**
     * Spring把Type(Class)映射成type，把name（变量名）映射成name
     * 优先按照自己指定的方式去寻找bean，否则才使用默认规则寻找bean
     *
     * @see Resource 是根据名称查找，你的成员属性长什么样，就会去找什么样的bean（匹配较为严格，类似于map，必须找到匹配的bean名
     * @see Autowired 是根据类型进行查找，其实也是在bean中查找，如果有接口有多个实现，但是只有一个被注入放入到bean，也是可以使用的
     */
    @Autowired
    private Executor asyncExecutor;

    private final ThreadUtil threadUtil = new ThreadUtil();

    @GetMapping("/simple")
    public void simple() {
        asyncService.simpleException();
    }

    @GetMapping("/sameClassTransfer")
    public void sameClassTransfer() {
        excludeException();
    }

    @GetMapping("/sameSubclassTransfer")
    public void sameSubclassTransfer() {
        asyncService.linkTransferException();
    }

    /**
     * 排除异常
     */
    @Async
    public void excludeException() {
        throw new IcBoLuoException();
    }


    @GetMapping("/spring")
    public void spring() {
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(threadUtil::sleep5s, asyncExecutor);
        threadUtil.sleep5s();
        voidCompletableFuture.join();
        System.out.println("method end");
    }

    /**
     * <p> Java守护线程：当Jvm检测到主线程或其他子线程执行完之后，守护线程也会马上停止执行
     * <p> 我们可以使用thread.setDaemon(true)来设置一个线程是守护线程还是非守护线程（默认false
     * <p> 当线程只剩下守护线程的时候，Jvm就会退出，还有其他任意一个用户线程还在，Jvm就不会退出
     * <p> 注意：thread.setDaemon(true)必须在Start之前，否则会抛出一个 IllegalThreadStateException(非法线程状态异常
     * <p> 在daemon 线程中产生的新线程也是daemon的
     * <p> 守护线程不能用于去访问固有资源，比如读写操作或者计算逻辑。因为它会在任何时候甚至在一个操作的中间发生中断
     * <p> Java自带的多线程框架，比如 ExecutorService 会将守护线程转换为用户线程（非守护线程，所以如果有使用后台线程就不能用Java的线程池
     * <p> 使用场景：当主线程结束时，结束其余的子线程（守护线程 自动关闭，避免了手动关闭子线程
     * <p> Java垃圾回收线程就是一个典型的守护线程；(当程序中不再有运行的Thread，程序就不会产生垃圾，垃圾回收器也就无事可做
     * <p> 内存资源或线程的管理，也可以用非守护线程
     */
    @GetMapping("/daemon")
    public void daemon() {
        // TODO 为什么放在Http中守护线程不生效，只有main方法中才生效
        Thread thread = new Thread(() -> threadUtil.infiniteLoop());
        thread.setDaemon(true);
        thread.start();
        log.warn("daemon method finish");
    }

    public static void main(String[] args) {
        Thread thread = new Thread(() -> new ThreadUtil().infiniteLoop());
        thread.setDaemon(true);
        thread.start();
        log.warn("daemon method finish");
    }
}
