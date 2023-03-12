package com.icboluo.designpattern.a4_behavior.template.improve;

/**
 * @author icboluo
 * @since 2020/11/15 20:53
 */
public abstract class SoyaMilk {
    /**
     * <p/>模板方法：make
     * <p/>模板方法可以做成final，不让子类重写覆盖
     */
    final void make() {
        select();
        if (customerWantCondiments()) {
            addCondiments();
        }
        soak();
        beat();
    }

    void select() {
        System.out.println("选择新鲜黄豆");
    }

    /**
     * 增加配料
     */
    abstract void addCondiments();

    void soak() {
        System.out.println("黄豆和配料开始浸泡");
    }

    void beat() {
        System.out.println("黄豆和配料放到豆浆机去打碎");
    }

    /**
     * 模板钩子方法，决定要不要添加每一个配料
     *
     * @return
     */
    boolean customerWantCondiments() {
        return true;
    }
}
