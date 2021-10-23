package icboluo.function;

/**
 * @author icboluo
 * @date 2021-29-12 22:29
 */
@FunctionalInterface
public interface DiConsumer<A, B, C, D> {

    void accept(A a, B b, C c, D d);
}
