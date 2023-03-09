package com.brian.bar.domain.table.event;

import com.brian.bar.generic.DomainEvent;

public class CostumerRemoved extends DomainEvent {
    protected String costumerID;
    public CostumerRemoved(String costumerID) {
        super("brian.bar.costumerremoved");
        this.costumerID = costumerID;
    }

    public String getCostumerID() {
        return costumerID;
    }
}
