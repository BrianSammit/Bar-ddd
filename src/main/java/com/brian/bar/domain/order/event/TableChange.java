package com.brian.bar.domain.order.event;

import com.brian.bar.generic.DomainEvent;

public class TableChange extends DomainEvent {
    private String tableID;
    private Integer tableNum;
    private String costumerID;

    public TableChange(String tableID, Integer tableNum, String costumerID) {
        super("brian.bar.tablechange");
        this.tableID = tableID;
        this.tableNum = tableNum;
        this.costumerID = costumerID;
    }

    public String getTableID() {
        return tableID;
    }

    public Integer getTableNum() {
        return tableNum;
    }

    public String getCostumerID() {
        return costumerID;
    }
}
