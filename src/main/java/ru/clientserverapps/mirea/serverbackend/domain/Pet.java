package ru.clientserverapps.mirea.serverbackend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;

public class Pet extends Product{
    int id;
    String type;
    String info;
    String additionalInfo;

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getInfo() {
        return info;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public int getInternalID() {
        return internalID;
    }

    @JsonIgnore
    int internalID;

    public Pet(int id, int count, String type, String info, BigDecimal price, String additionalInfo, int internalID) {
        this.id = id;
        this.count = count;
        this.type = type;
        this.info = info;
        this.price = price;
        this.additionalInfo = additionalInfo;
        this.internalID = internalID;
    }
}
