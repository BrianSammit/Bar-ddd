package com.brian.bar.domain.order.event;

import com.brian.bar.generic.DomainEvent;

public class OrderCreated extends DomainEvent {

    private String status;
    private String modification;
    private String tableID;
    private String drinkID;

    public OrderCreated(String status, String modification, String tableID, String drinkID) {
        super("brian.bar.ordercreated");
        this.status = status;
        this.modification = modification;
        this.tableID = tableID;
        this.drinkID = drinkID;
    }

    public String getStatus() {
        return status;
    }

    public String getModification() {
        return modification;
    }

    public String getTableID() {
        return tableID;
    }

    public String getDrinkID() {
        return drinkID;
    }
}
