package com.brian.bar.domain.table.command;

import com.brian.bar.generic.Command;

public class CreateTableCommand extends Command {

    private String id;
    private Integer tableNum;
    private String waiter;
    private String costumerID;

    public CreateTableCommand(String id, Integer tableNum, String waiter, String costumerID) {
        this.id = id;
        this.tableNum = tableNum;
        this.waiter = waiter;
        this.costumerID = costumerID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getTableNum() {
        return tableNum;
    }

    public void setTableNum(Integer tableNum) {
        this.tableNum = tableNum;
    }

    public String getWaiter() {
        return waiter;
    }

    public void setWaiter(String waiter) {
        this.waiter = waiter;
    }

    public String getCostumerID() {
        return costumerID;
    }

    public void setCostumerID(String costumerID) {
        this.costumerID = costumerID;
    }
}
