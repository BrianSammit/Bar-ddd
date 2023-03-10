package com.brian.bar.domain.table.event;

import com.brian.bar.generic.DomainEvent;

public class WaiterAdded extends DomainEvent {

    private String waiterID;
    private String name;


    public WaiterAdded(String waiterID, String name) {
        super("brian.bar.waiteradded");
        this.waiterID = waiterID;
        this.name = name;
    }

    public String getWaiterID() {
        return waiterID;
    }

    public String getName() {
        return name;
    }
}
