package com.brian.bar.domain.table;

import com.brian.bar.domain.drink.Drink;
import com.brian.bar.domain.drink.values.DrinkID;
import com.brian.bar.domain.drink.values.Name;
import com.brian.bar.domain.order.event.DrinkAdded;
import com.brian.bar.domain.order.event.DrinkRemoved;
import com.brian.bar.domain.table.event.CostumerAdded;
import com.brian.bar.domain.table.event.CostumerRemoved;
import com.brian.bar.domain.table.event.TableCreated;
import com.brian.bar.domain.table.values.CostumerID;
import com.brian.bar.domain.table.values.TableNum;
import com.brian.bar.domain.table.values.Waiter;
import com.brian.bar.generic.EventChange;

import java.util.ArrayList;

public class TableEventChange extends EventChange {

    public TableEventChange(Table table) {
        apply((TableCreated event) -> {
            table.tableNum = new TableNum(event.getTableNum());
            table.waiter = new Waiter(event.getWaiter());
            table.costumers = new ArrayList<>();
        });

        apply((CostumerAdded event) -> {
            Costumer costumer = new Costumer(
                    CostumerID.of(event.getCostumerID()),
                    new Name(event.getName())
            );
            table.costumers.add(costumer);
        });

        apply((CostumerRemoved event) -> {
            table.costumers.removeIf(costumer -> costumer.identity().value().equals(event.getCostumerID()));
        });
    }
}
