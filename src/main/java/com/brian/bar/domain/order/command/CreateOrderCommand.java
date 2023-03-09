package com.brian.bar.domain.order.command;

import com.brian.bar.generic.Command;

public class CreateOrderCommand extends Command {
    private String id;
    private String status;
    private String modification;
    private String tableID;
    private String drinkID;

    public CreateOrderCommand(String id, String status, String modification, String tableID, String drinkID) {
        this.id = id;
        this.status = status;
        this.modification = modification;
        this.tableID = tableID;
        this.drinkID = drinkID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getModification() {
        return modification;
    }

    public void setModification(String modification) {
        this.modification = modification;
    }

    public String getTableID() {
        return tableID;
    }

    public void setTableID(String tableID) {
        this.tableID = tableID;
    }

    public String getDrinkID() {
        return drinkID;
    }

    public void setDrinkID(String drinkID) {
        this.drinkID = drinkID;
    }
}
