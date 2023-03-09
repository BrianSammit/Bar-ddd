package com.brian.bar.domain.table;

import com.brian.bar.domain.table.event.TableCreated;
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
        } );
    }
}
