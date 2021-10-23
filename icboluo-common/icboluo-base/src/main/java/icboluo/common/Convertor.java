package icboluo.common;

/**
 * 客户端对象、领域对象、数据对象的转换器
 *
 * @param <C> 客户端对象
 * @param <E> 领域对象
 * @param <D> 数据对象
 * @param <V> 视图对象
 * @author icboluo
 */
@SuppressWarnings("unused")
public interface Convertor<C, E, D, V> {

    default C entityToClient(E entityObject) {
        return null;
    }

    default C dataToClient(D dataObject) {
        return null;
    }

    default E clientToEntity(C clientObject) {
        return null;
    }

    default E dataToEntity(D dataObject) {
        return null;
    }

    default D entityToData(E entityObject) {
        return null;
    }

    /**
     * 实体对象转换成为视图对象
     *
     * @param dataObject 实体对象
     * @return 视图对象
     */
    default V toView(D dataObject) {
        return null;
    }
}

