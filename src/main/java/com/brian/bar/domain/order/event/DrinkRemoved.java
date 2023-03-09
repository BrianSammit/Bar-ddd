package com.brian.bar.domain.order.event;

import com.brian.bar.generic.DomainEvent;

public class DrinkRemoved extends DomainEvent {
    protected String drinkID;

    public DrinkRemoved(String drinkID) {
        super("brian.bar.drinkremoved");
        this.drinkID = drinkID;
    }

    public String getDrinkID() {
        return drinkID;
    }
}
