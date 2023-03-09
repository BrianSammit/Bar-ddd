package com.brian.bar.domain.table.values;

import com.brian.bar.generic.Identity;

public class TableID extends Identity {
    public TableID() {
    }

    private TableID(String id) {
        super(id);
    }

    public static TableID of(String id){
        return new TableID(id);
    }

}
