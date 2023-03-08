package com.brian.bar.domain.values;

import com.brian.bar.generic.Identity;

public class OrderID extends Identity {
    public OrderID() {}

    private OrderID(String id) {
        super(id);
    }

    public static OrderID of(String id) {
        return new OrderID(id);
    }
}
