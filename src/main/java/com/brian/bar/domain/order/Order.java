package com.brian.bar.domain.order;

import com.brian.bar.domain.drink.Drink;
import com.brian.bar.domain.drink.values.DrinkID;
import com.brian.bar.domain.drink.values.Name;
import com.brian.bar.domain.drink.values.Price;
import com.brian.bar.domain.order.event.DrinkAdded;
import com.brian.bar.domain.order.event.OrderCreated;
import com.brian.bar.domain.order.values.Modification;
import com.brian.bar.domain.order.values.OrderID;
import com.brian.bar.domain.order.values.Status;
import com.brian.bar.domain.table.values.TableID;
import com.brian.bar.generic.AggregateRoot;
import com.brian.bar.generic.DomainEvent;

import java.util.List;
import java.util.Objects;

public class Order extends AggregateRoot<OrderID> {

    protected Status status;
    protected Modification modification;
    protected TableID tableID;
    protected List<Drink> drinks;

    public Order(OrderID id, Status status, Modification modification, TableID tableID, DrinkID drinkID) {
        super(id);
        subscribe(new OrderEventChange(this));
        appendChange(new OrderCreated(status.value(), modification.value(), tableID.value(), drinkID.value())).apply();
    }

    private Order(OrderID id){
        super(id);
        subscribe(new OrderEventChange(this));
    }

    public static Order from(OrderID id, List<DomainEvent> events){
        Order order = new Order(id);
        events.forEach(order::applyEvent);
        return  order;
    }

    public void addDrink(DrinkID drinkID, Name name, Price price, Modification modification){
        Objects.requireNonNull(drinkID);
        Objects.requireNonNull(name);
        Objects.requireNonNull(price);
        Objects.requireNonNull(modification);
        appendChange(new DrinkAdded(drinkID.value(), name.value(), price.value(), modification.value()));
    }
}