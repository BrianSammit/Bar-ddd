package com.brian.bar.domain.order;

import com.brian.bar.domain.drink.Drink;
import com.brian.bar.domain.drink.values.DrinkID;
import com.brian.bar.domain.order.event.DrinkAdded;
import com.brian.bar.domain.order.event.DrinkRemoved;
import com.brian.bar.domain.order.event.OrderCreated;
import com.brian.bar.domain.order.values.Modification;
import com.brian.bar.domain.order.values.Status;
import com.brian.bar.domain.table.values.TableID;
import com.brian.bar.generic.EventChange;

import java.util.ArrayList;

public class OrderEventChange extends EventChange {
    public OrderEventChange(Order order){
        apply((OrderCreated event) -> {
            order.status = new Status(event.getStatus());
            order.modification = new Modification(event.getModification());
            order.tableID = TableID.of(event.getTableID());
            order.drinks = new ArrayList<>();
        });

        apply((DrinkAdded event) -> {
            Drink drink = new Drink(
                    DrinkID.of(event.getDrinkID())
            );
            order.drinks.add(drink);
        });

        apply((DrinkRemoved event) -> {
            order.drinks.removeIf(drink -> drink.identity().value().equals(event.getDrinkID()));
        });
    }
}
