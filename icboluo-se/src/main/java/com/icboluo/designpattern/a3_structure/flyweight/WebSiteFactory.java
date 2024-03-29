package com.icboluo.designpattern.a3_structure.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * 网站工厂类：根据需要返回一个网站
 *
 * @author icboluo
 * @since 2020/12/26 16:52
 */
class WebSiteFactory {
    /**
     * 集合，充当池的作用
     */
    private Map<String, ConcreteWebSite> pool = new HashMap<>();

    /**
     * 根据网站的类型，返回一个网站，如果没有网站，那就创建一个网站，并放入到池中，并返回
     *
     * @return
     */
    public WebSite getWebSiteCategory(String type) {
        if (!pool.containsKey(type)) {
            pool.put(type, new ConcreteWebSite(type));
        }
        return pool.get(type);
    }

    public int getWebSiteCount() {
        return pool.size();
    }
}
