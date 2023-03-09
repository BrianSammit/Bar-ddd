package com.brian.bar.domain.order.command;

import com.brian.bar.generic.Command;

public class RemoveDrinkCommand extends Command {
    private String drinkID;
    private String orderID;

    public RemoveDrinkCommand(String drinkID, String orderID) {
        this.drinkID = drinkID;
        this.orderID = orderID;
    }

    public String getDrinkID() {
        return drinkID;
    }

    public String getOrderID() {
        return orderID;
    }
}
