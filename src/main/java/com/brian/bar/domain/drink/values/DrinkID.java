package com.brian.bar.domain.drink.values;

import com.brian.bar.generic.Identity;

public class DrinkID extends Identity {
    public DrinkID() {
    }

    private DrinkID(String id) {
        super(id);
    }

    public static DrinkID of(String id){
       return new DrinkID(id);
    }
}
