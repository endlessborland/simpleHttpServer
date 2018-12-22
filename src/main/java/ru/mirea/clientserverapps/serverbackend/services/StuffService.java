package ru.mirea.clientserverapps.serverbackend.services;

import ru.mirea.clientserverapps.serverbackend.exceptions.IDNotFoundException;
import ru.mirea.clientserverapps.serverbackend.exceptions.NotEnoughInstancesException;
import ru.mirea.clientserverapps.serverbackend.models.Stuff;
import ru.mirea.clientserverapps.serverbackend.models.User;

import java.util.List;

public interface StuffService {
    public abstract Stuff getStuff(int id) throws IDNotFoundException;
    public abstract List<Stuff> getStuffs();
    public abstract void buyStuff(int id, int amount, User user) throws IDNotFoundException, NotEnoughInstancesException;
}
