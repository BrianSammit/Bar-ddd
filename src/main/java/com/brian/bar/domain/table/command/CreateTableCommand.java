package com.brian.bar.domain.table.command;

import com.brian.bar.generic.Command;

public class CreateTableCommand extends Command {

    private String id;
    private Integer tableNum;
    private String costumerID;

    public CreateTableCommand(String id, Integer tableNum, String costumerID) {
        this.id = id;
        this.tableNum = tableNum;
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

    public String getCostumerID() {
        return costumerID;
    }

    public void setCostumerID(String costumerID) {
        this.costumerID = costumerID;
    }
}
