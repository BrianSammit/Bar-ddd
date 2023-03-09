package com.brian.bar.domain.drink.values;

import com.brian.bar.generic.ValueObject;

import java.util.Objects;

public class Price implements ValueObject<Float> {

    private final Float value;

    public Price(Float value) {
        this.value = Objects.requireNonNull(value);
        if(this.value < 0){
            throw new IllegalArgumentException();
        }
    }
    @Override
    public Float value() {
        return value;
    }
}
