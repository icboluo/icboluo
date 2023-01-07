package com.icboluo.designpattern.create.prototype.address_deepcopy;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author icboluo
 * @since 2023-01-07 15:50
 */
@Data
@AllArgsConstructor
class User implements Cloneable {
    private String name;

    private Address address;

    @Override
    protected User clone() throws CloneNotSupportedException {
        User user = (User) super.clone();
        user.setAddress(this.address.clone());
        return user;
    }
}
