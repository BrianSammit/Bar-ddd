package com.brian.bar.domain.table;

import com.brian.bar.domain.table.values.TableID;
import com.brian.bar.generic.AggregateRoot;

public class Table extends AggregateRoot<TableID> {
    public Table(TableID id) {
        super(id);
    }
}
