package com.brian.bar.domain.table.values;

import com.brian.bar.generic.ValueObject;

import java.util.Objects;

public class TableNum implements ValueObject<Integer> {
    private final Integer value;

    public TableNum(Integer value) {
        this.value = Objects.requireNonNull(value);
        if(this.value < 0){
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Integer value() {
        return value;
    }
}
