package com.brian.bar.domain.table.event;

import com.brian.bar.generic.DomainEvent;

public class CostumerAdded extends DomainEvent {

    private String costumerID;
    private String name;

    public CostumerAdded(String costumerID, String name) {
        super("brian.bar.costumeradded");
        this.costumerID = costumerID;
        this.name = name;
    }

    public String getCostumerID() {
        return costumerID;
    }

    public String getName() {
        return name;
    }
}
