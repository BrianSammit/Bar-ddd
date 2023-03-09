package com.brian.bar.domain.table;

import com.brian.bar.domain.order.Order;
import com.brian.bar.domain.order.OrderEventChange;
import com.brian.bar.domain.order.values.OrderID;
import com.brian.bar.domain.table.event.TableCreated;
import com.brian.bar.domain.table.values.CostumerID;
import com.brian.bar.domain.table.values.TableID;
import com.brian.bar.domain.table.values.TableNum;
import com.brian.bar.domain.table.values.Waiter;
import com.brian.bar.generic.AggregateRoot;
import com.brian.bar.generic.DomainEvent;

import java.util.List;

public class Table extends AggregateRoot<TableID> {

    protected TableNum tableNum;
    protected Waiter waiter;
    protected List<Costumer> costumers;
    public Table(TableID id, TableNum tableNum, Waiter waiter, CostumerID costumerID) {
        super(id);
        subscribe(new TableEventChange(this));
        appendChange(new TableCreated(tableNum.value(), waiter.value(), costumerID.value())).apply();
    }

    private Table(TableID id){
        super(id);
        subscribe(new TableEventChange(this));
    }

    public static Table from(TableID id, List<DomainEvent> events){
        Table table = new Table(id);
        events.forEach(table::applyEvent);
        return table;
    }
}
