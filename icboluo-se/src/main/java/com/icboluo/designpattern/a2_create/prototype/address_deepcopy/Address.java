package com.icboluo.designpattern.a2_create.prototype.address_deepcopy;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author icboluo
 * @since 2023-01-07 15:49
 */
@Data
@AllArgsConstructor
class Address implements Cloneable {
    private String county;
    private String city;

    @Override
    protected Address clone() throws CloneNotSupportedException {
        return (Address) super.clone();
    }
}
