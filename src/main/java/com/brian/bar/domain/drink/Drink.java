package com.brian.bar.domain.drink;

import com.brian.bar.domain.drink.values.DrinkID;
import com.brian.bar.generic.AggregateRoot;

public class Drink extends AggregateRoot<DrinkID> {
    public Drink(DrinkID id) {
        super(id);
    }
}
