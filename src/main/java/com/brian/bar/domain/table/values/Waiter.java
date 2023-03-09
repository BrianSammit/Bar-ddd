package com.brian.bar.domain.table.values;

import com.brian.bar.generic.ValueObject;

import java.util.Objects;

public class Waiter implements ValueObject<String> {

    private final String value;

    public Waiter(String value) {
        this.value = Objects.requireNonNull(value);
        if(this.value.isEmpty()){
            throw new IllegalArgumentException();
        }
    }
    @Override
    public String value() {
        return value;
    }
}
