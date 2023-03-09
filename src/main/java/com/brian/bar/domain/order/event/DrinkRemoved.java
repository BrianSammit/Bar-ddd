package com.brian.bar.domain.order.event;

import com.brian.bar.generic.DomainEvent;

public class DrinkRemoved extends DomainEvent {
    protected String drinkID;

    public DrinkRemoved(String type, String drinkID) {
        super(type);
        this.drinkID = drinkID;
    }

    public String getDrinkID() {
        return drinkID;
    }
}
