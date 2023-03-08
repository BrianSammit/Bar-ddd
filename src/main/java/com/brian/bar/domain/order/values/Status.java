package com.brian.bar.domain.order.values;

import com.brian.bar.generic.ValueObject;

import java.util.Objects;

public class Status implements ValueObject<String> {
    private final String value;

    public Status(String value) {
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
