package ru.mirea.clientserverapps.serverbackend.models;

import java.math.BigDecimal;

public class PetWrapper {
    int id;
    BigDecimal price;
    String type;
    String info;

    public void setId(int id) {
        this.id = id;
    }

    public void setPrice(String price) {
        this.price = new BigDecimal(price);
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getId() {
        return id;
    }

    public String getPrice() {
        return price.toString();
    }

    public String getType() {
        return type;
    }

    public String getInfo() {
        return info;
    }

    public PetWrapper(int id, String type, String info, String price) {
        this.id = id;
        this.price = new BigDecimal(price);
        this.type = type;
        this.info = info;
    }

    public PetWrapper(Pet pet)
    {
        this.id = pet.id;
        this.price = pet.price;
        this.type = pet.type;
        this.info = pet.info;
    }
}
