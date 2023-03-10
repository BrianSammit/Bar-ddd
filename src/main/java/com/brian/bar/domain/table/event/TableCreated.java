package com.brian.bar.domain.table.event;

import com.brian.bar.generic.DomainEvent;

public class TableCreated extends DomainEvent {
    private Integer tableNum;
    private String costumerID;

    public TableCreated(Integer tableNum, String costumerID) {
        super("brian.bar.tablecreated");
        this.tableNum = tableNum;
        this.costumerID = costumerID;
    }

    public Integer getTableNum() {
        return tableNum;
    }

    public String getCostumerID() {
        return costumerID;
    }
}
