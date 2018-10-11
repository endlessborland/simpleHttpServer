package ru.clientserverapps.mirea.serverbackend;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ru.clientserverapps.mirea.serverbackend.domain.Pet;

import java.math.BigDecimal;

public class PetWrapper {
    public int getId() {
        return id;
    }

    public int getInternalID() {
        return internalID;
    }

    public int getCount() {
        return count;
    }

    public BigDecimal getPrice() {
        return price;
    }

    private int id;
    @JsonIgnore
    private
    int internalID;
    private int count;
    private BigDecimal price;

    PetWrapper(Pet instance)
    {
        this.id = instance.getId();
        this.internalID = instance.getInternalID();
        this.count = instance.getCount();
        this.price = instance.getPrice();
    }
}
