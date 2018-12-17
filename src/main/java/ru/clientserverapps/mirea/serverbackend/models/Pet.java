package ru.clientserverapps.mirea.serverbackend.models;

import java.math.BigDecimal;

public class Pet extends Product{
    String type;
    String info;
    String additionalInfo;

    public Pet(int id, int count, String type, String info, String additionalInfo, String price) {
        this.id = id;
        this.count = count;
        this.type = type;
        this.info = info;
        this.price = new BigDecimal(price);
        this.additionalInfo = additionalInfo;
    }

    public String getType() {
        return type;
    }

    public String getInfo() {
        return info;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }
}
