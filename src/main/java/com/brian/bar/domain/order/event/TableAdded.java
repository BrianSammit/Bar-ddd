package com.brian.bar.domain.order.event;

import com.brian.bar.generic.DomainEvent;

public class TableAdded extends DomainEvent {
    private String tableID;
    private Integer tableNum;
    private String costumerID;

    public TableAdded(String tableID, Integer tableNum, String costumerID) {
        super("brian.bar.tableadded");
        this.tableID = tableID;
        this.tableNum = tableNum;
        this.costumerID = costumerID;
    }

    public Integer getTableNum() {
        return tableNum;
    }

    public String getCostumerID() {
        return costumerID;
    }

    public String getTableID() {
        return tableID;
    }
}
