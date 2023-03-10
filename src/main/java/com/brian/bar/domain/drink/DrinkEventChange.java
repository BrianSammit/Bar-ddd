package com.brian.bar.domain.drink;

import com.brian.bar.domain.drink.event.DrinkCreated;
import com.brian.bar.domain.drink.values.Name;
import com.brian.bar.domain.drink.values.Price;
import com.brian.bar.domain.order.values.Modification;
import com.brian.bar.generic.EventChange;

public class DrinkEventChange extends EventChange {

    public DrinkEventChange(Drink drink){
        apply((DrinkCreated event) -> {
            drink.name = new Name(event.getName());
            drink.price = new Price(event.getPrice());
            drink.modification = new Modification(event.getModification());
        });
    }
}
