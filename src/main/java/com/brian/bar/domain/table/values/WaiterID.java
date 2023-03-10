package com.brian.bar.domain.table.values;

import com.brian.bar.generic.Identity;
import com.brian.bar.generic.ValueObject;

import java.util.Objects;

public class WaiterID extends Identity {
    public WaiterID() {
    }

    private WaiterID(String id) {
        super(id);
    }

    public static WaiterID of(String id){
        return new WaiterID(id);
    }

}
