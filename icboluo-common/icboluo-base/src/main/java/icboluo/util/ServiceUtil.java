package icboluo.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author icboluo
 * @date 2021-40-01 21:40
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ServiceUtil {

    public static void main(String[] args) {
        List<Integer> subList = Arrays.asList(1, 2, 3);
        add(ServiceUtil::mainAdd, ServiceUtil::subAdd, 7, subList);
    }

    private static void mainAdd(Integer i) {
        System.out.println(i);
    }

    private static void subAdd(List<Integer> list) {
        list.forEach(System.out::println);
    }

    /**
     * 主子表新增
     */
    public static <M, S> void add(Consumer<M> mainInsert, Consumer<List<S>> subListInsert, M main, List<S> sub) {
        mainInsert.accept(main);
        if (!CollectionUtils.isEmpty(sub)) {
            subListInsert.accept(sub);
        }
    }

    public static <M, S, K> void update(Consumer<M> mainUpdate, Consumer<List<S>> subListUpdate, Consumer<K> subDel, M main, List<S> sub, K id) {
        mainUpdate.accept(main);
        subDel.accept(id);
        if (!CollectionUtils.isEmpty(sub)) {
            subListUpdate.accept(sub);
        }
    }
}
