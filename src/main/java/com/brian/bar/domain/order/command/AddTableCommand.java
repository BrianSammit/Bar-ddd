package com.brian.bar.domain.order.command;

import com.brian.bar.generic.Command;

public class AddTableCommand extends Command {
    private String tableID;
    private Integer tableNum;
    private String costumerID;
    private String orderID;

    public AddTableCommand(String tableID, Integer tableNum, String costumerID, String orderID) {
        this.tableID = tableID;
        this.tableNum = tableNum;
        this.costumerID = costumerID;
        this.orderID = orderID;
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

    public String getOrderID() {
        return orderID;
    }
}
