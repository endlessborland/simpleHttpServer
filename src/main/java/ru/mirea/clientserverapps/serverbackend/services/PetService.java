package ru.mirea.clientserverapps.serverbackend.services;

import ru.mirea.clientserverapps.serverbackend.models.PetWrapper;
import ru.mirea.clientserverapps.serverbackend.exceptions.IDNotFoundException;
import ru.mirea.clientserverapps.serverbackend.exceptions.NotEnoughInstancesException;
import ru.mirea.clientserverapps.serverbackend.models.Pet;
import ru.mirea.clientserverapps.serverbackend.models.User;

import java.util.List;

public interface PetService {
    public abstract Pet getPet(int id) throws IDNotFoundException;
    public abstract List<PetWrapper> getPets();
    public abstract void buyPet(int id, int amount, User user) throws IDNotFoundException, NotEnoughInstancesException;
}
