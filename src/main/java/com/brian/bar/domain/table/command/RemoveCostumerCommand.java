package com.brian.bar.domain.table.command;

import com.brian.bar.generic.Command;

public class RemoveCostumerCommand extends Command {

    private String costumerID;
    private String tableID;

    public RemoveCostumerCommand(String costumerID, String tableID) {
        this.costumerID = costumerID;
        this.tableID = tableID;
    }

    public String getCostumerID() {
        return costumerID;
    }

    public String getTableID() {
        return tableID;
    }
}
