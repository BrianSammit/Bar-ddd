package com.brian.bar.domain.drink.command;

import com.brian.bar.generic.Command;

public class CreateDrinkCommand extends Command {

    private String id;
    private String name;
    private Float price;
    private String modification;

    public CreateDrinkCommand(String id, String name, Float price, String modification) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.modification = modification;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getModification() {
        return modification;
    }

    public void setModification(String modification) {
        this.modification = modification;
    }
}
