package ru.clientserverapps.mirea.serverbackend.domain;

import java.math.BigDecimal;

public class Product {
    protected BigDecimal price;
    protected int count;

    public int getCount() {
        return count;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
