package com.brian.bar.domain.table;

import com.brian.bar.domain.drink.values.Name;
import com.brian.bar.domain.table.values.CostumerID;
import com.brian.bar.generic.Entity;

public class Costumer extends Entity<CostumerID> {
    private Name name;
    public Costumer(CostumerID id, Name name) {
        super(id);
        this.name = name;
    }

    public Name getName() {
        return name;
    }
}
