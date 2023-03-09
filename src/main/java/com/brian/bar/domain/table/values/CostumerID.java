package com.brian.bar.domain.table.values;

import com.brian.bar.generic.Identity;

public class CostumerID extends Identity {
    public CostumerID() {
    }

    private CostumerID(String id) {
        super(id);
    }

    public static CostumerID of(String id){
        return new CostumerID(id);
    }
}
