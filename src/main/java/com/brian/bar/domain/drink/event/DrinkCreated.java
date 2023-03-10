package com.brian.bar.domain.drink.event;

import com.brian.bar.generic.DomainEvent;

public class DrinkCreated extends DomainEvent {
    private String name;
    private Float price;
    private String modification;

    public DrinkCreated(String name, Float price, String modification) {
        super("brian.bar.drinkcreated");
        this.name = name;
        this.price = price;
        this.modification = modification;
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
