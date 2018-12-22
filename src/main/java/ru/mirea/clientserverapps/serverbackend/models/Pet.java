package ru.mirea.clientserverapps.serverbackend.models;

import ru.mirea.clientserverapps.serverbackend.enums.ItemType;

import java.math.BigDecimal;

public class Pet extends Product{
    String type;
    String info;
    String additionalInfo;

    public Pet(int id, int count, String type, String info, String additionalInfo, String price, ItemType itemType) {
        this.id = id;
        this.count = count;
        this.type = type;
        this.info = info;
        this.price = new BigDecimal(price);
        this.additionalInfo = additionalInfo;
        this.itemType = ItemType.TypePet;
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
