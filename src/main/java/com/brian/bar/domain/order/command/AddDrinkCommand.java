package com.brian.bar.domain.order.command;

public class AddDrinkCommand {
    private String drinkID;
    private String name;
    private Float price;
    private String modification;
    private String orderID;

    public AddDrinkCommand(String drinkID, String name, Float price, String modification, String orderID) {
        this.drinkID = drinkID;
        this.name = name;
        this.price = price;
        this.modification = modification;
        this.orderID = orderID;
    }

    public String getDrinkID() {
        return drinkID;
    }

    public String getName() {
        return name;
    }

    public Float getPrice() {
        return price;
    }

    public String getModification() {
        return modification;
    }

    public String getOrderID() {
        return orderID;
    }
}
