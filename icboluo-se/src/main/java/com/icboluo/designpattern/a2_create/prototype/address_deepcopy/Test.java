package com.icboluo.designpattern.a2_create.prototype.address_deepcopy;

/**
 * @author icboluo
 * @since 2023-01-07 15:52
 */
class Test {
    public static void main(String[] args) throws CloneNotSupportedException {
        Address address = new Address("杭州", "中国");
        User user = new User("大山", address);
        // 调用clone方法进行深拷贝
        User copyUser = user.clone();
        // 修改源对象的值
        user.getAddress().setCity("深圳");
        // 检查两个对象的值不同
        System.out.println(!user.getAddress().getCity().equals(copyUser.getAddress().getCity()));
        System.out.println(!user.getAddress().equals(copyUser.getAddress()));
    }
}
