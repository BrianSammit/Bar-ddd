package com.brian.bar.domain.order.event;

import com.brian.bar.generic.DomainEvent;

public class OrderCreated extends DomainEvent {

    protected String status;
    protected String modification;
    protected String tableID;
    protected String drinkID;

    public OrderCreated(String type, String status, String modification, String tableID, String drinkID) {
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
