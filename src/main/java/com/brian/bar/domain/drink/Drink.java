package com.brian.bar.domain.drink;

import com.brian.bar.domain.drink.event.DrinkCreated;
import com.brian.bar.domain.drink.values.DrinkID;
import com.brian.bar.domain.drink.values.Name;
import com.brian.bar.domain.drink.values.Price;
import com.brian.bar.domain.order.values.Modification;
import com.brian.bar.generic.AggregateRoot;
import com.brian.bar.generic.DomainEvent;

import java.util.List;

public class Drink extends AggregateRoot<DrinkID> {

    protected Name name;
    protected Price price;
    protected Modification modification;

    public Drink(DrinkID id, Name name, Price price, Modification modification) {
        super(id);
        subscribe(new DrinkEventChange(this));
        appendChange(new DrinkCreated(name.value(), price.value(), modification.value())).apply();
    }

    private Drink(DrinkID id) {
        super(id);
        subscribe(new DrinkEventChange(this));
    }


    public static Drink from(DrinkID id, List<DomainEvent> events){
        Drink drink = new Drink(id);
        events.forEach(drink::applyEvent);
        return drink;
    }
}
