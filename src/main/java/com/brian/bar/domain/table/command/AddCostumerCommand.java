package com.brian.bar.domain.table.command;

import com.brian.bar.generic.Command;

public class AddCostumerCommand extends Command {
    private String costumerID;
    private String name;
    private String tableID;

    public AddCostumerCommand(String costumerID, String name, String tableID) {
        this.costumerID = costumerID;
        this.name = name;
        this.tableID = tableID;
    }

    public String getCostumerID() {
        return costumerID;
    }

    public void setCostumerID(String costumerID) {
        this.costumerID = costumerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTableID() {
        return tableID;
    }

    public void setTableID(String tableID) {
        this.tableID = tableID;
    }
}
