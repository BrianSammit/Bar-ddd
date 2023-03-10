package com.brian.bar.domain.table.command;

import com.brian.bar.generic.Command;

public class AddWaiterCommand extends Command {
    private String waiterID;
    private String name;
    private String tableID;

    public AddWaiterCommand(String waiterID, String name, String tableID) {
        this.waiterID = waiterID;
        this.name = name;
        this.tableID = tableID;
    }

    public String getWaiterID() {
        return waiterID;
    }

    public String getTableID() {
        return tableID;
    }

    public String getName() {
        return name;
    }
}
