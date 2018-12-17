package ru.mirea.clientserverapps.serverbackend.models;

import java.math.BigDecimal;

public class Product {

    protected BigDecimal price;
    protected int count;
    protected int id;

    public void setPrice(String price) {
        this.price = new BigDecimal(price);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getId() { return id; }

    public int getCount() {
        return count;
    }

    public String getPrice() {
        return price.toString();
    }

}
