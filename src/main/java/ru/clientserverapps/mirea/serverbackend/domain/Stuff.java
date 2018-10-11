package ru.clientserverapps.mirea.serverbackend.domain;

import java.math.BigDecimal;

public class Stuff extends Product{

    String name;
    String description;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }

    int id;

    public Stuff(int id, String name, String description, BigDecimal price, int count) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.count = count;
    }
}

