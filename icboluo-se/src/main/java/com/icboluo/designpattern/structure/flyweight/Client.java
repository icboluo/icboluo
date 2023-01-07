package com.icboluo.designpattern.structure.flyweight;

/**
 * 享元模式
 *
 * @author icboluo
 * @since 2020/12/26 16:48
 */
class Client {
    public static void main(String[] args) {
        WebSiteFactory webSiteFactory = new WebSiteFactory();
        WebSite webSite01 = webSiteFactory.getWebSiteCategory("新闻");
        webSite01.use(new User("tom"));
        WebSite webSite02 = webSiteFactory.getWebSiteCategory("博客");
        webSite02.use(new User("jack"));
        WebSite webSite03 = webSiteFactory.getWebSiteCategory("博客");
        webSite03.use(new User("ket"));
        System.out.println(webSiteFactory.getWebSiteCount());
    }
}
