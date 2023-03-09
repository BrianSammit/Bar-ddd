package com.brian.bar.domain.order.event;

import com.brian.bar.generic.DomainEvent;

public class DrinkAdded extends DomainEvent {

    protected String drinkID;
    protected String name;
    protected Float price;
    protected String modification;

    public DrinkAdded(String drinkID, String name, Float price, String modification) {
        super("brian.bar.drinkadded");
        this.drinkID = drinkID;
        this.name = name;
        this.price = price;
        this.modification = modification;
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
}
