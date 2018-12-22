package ru.mirea.clientserverapps.serverbackend.services;

import ru.mirea.clientserverapps.serverbackend.exceptions.IDNotFoundException;
import ru.mirea.clientserverapps.serverbackend.exceptions.NotEnoughInstancesException;
import ru.mirea.clientserverapps.serverbackend.models.Product;
import ru.mirea.clientserverapps.serverbackend.models.ProductTrayWrapper;
import ru.mirea.clientserverapps.serverbackend.models.User;

import java.util.List;

public interface TrayService {
    public abstract void removeFromTray(User user, int id) throws IDNotFoundException;
    public abstract List<ProductTrayWrapper> getTrayContent(User user);
    public abstract void checkout(User user) throws IDNotFoundException, NotEnoughInstancesException;
}
