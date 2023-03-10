package com.brian.bar.domain.table;

import com.brian.bar.domain.drink.values.Name;
import com.brian.bar.domain.table.values.WaiterID;
import com.brian.bar.generic.Entity;

public class Waiter extends Entity<WaiterID> {
    private Name name;
    public Waiter(WaiterID id, Name name) {
        super(id);
        this.name = name;
    }

    public Name getName() {
        return name;
    }
}
