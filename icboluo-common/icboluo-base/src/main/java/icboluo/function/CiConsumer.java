package icboluo.function;

/**
 * @author icboluo
 * @date 2021-29-12 22:29
 */
@FunctionalInterface
public interface CiConsumer<A, B, C> {

    void accept(A a, B b, C c);
}
