package com.brian.bar.domain.order;

import com.brian.bar.domain.drink.Drink;
import com.brian.bar.domain.drink.values.DrinkID;
import com.brian.bar.domain.drink.values.Name;
import com.brian.bar.domain.drink.values.Price;
import com.brian.bar.domain.order.event.*;
import com.brian.bar.domain.order.values.Modification;
import com.brian.bar.domain.order.values.OrderID;
import com.brian.bar.domain.order.values.Status;
import com.brian.bar.domain.table.Table;
import com.brian.bar.domain.table.values.TableID;
import com.brian.bar.generic.AggregateRoot;
import com.brian.bar.generic.DomainEvent;

import java.util.List;
import java.util.Objects;

public class Order extends AggregateRoot<OrderID> {

    protected Status status;
    protected Modification modification;
    protected TableID tableID;
    protected  Table table;
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

    public void addDrink(DrinkID drinkID, String name, Float price, String  modification){
        Objects.requireNonNull(drinkID);
        Objects.requireNonNull(name);
        Objects.requireNonNull(price);
        Objects.requireNonNull(modification);
        appendChange(new DrinkAdded(drinkID.value(), name, price, modification)).apply();
    }

    public void removeDrink(String drinkID){
        Objects.requireNonNull(drinkID);
        appendChange(new DrinkRemoved(drinkID)).apply();
    }

    public void addTable(TableID tableID, Integer tableNum, String costumerID){
        Objects.requireNonNull(tableID);
        Objects.requireNonNull(tableNum);
        Objects.requireNonNull(costumerID);
        appendChange(new TableAdded(tableID.value(), tableNum, costumerID )).apply();
    }

    public void changeTable(TableID tableID, Integer tableNum, String costumerID){
        Objects.requireNonNull(tableID);
        Objects.requireNonNull(tableNum);
        Objects.requireNonNull(costumerID);
        appendChange(new TableChange(tableID.value(), tableNum, costumerID )).apply();
    }
}
