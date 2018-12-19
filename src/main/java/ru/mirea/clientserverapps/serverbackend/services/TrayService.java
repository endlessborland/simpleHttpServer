package ru.mirea.clientserverapps.serverbackend.services;

import ru.mirea.clientserverapps.serverbackend.models.Product;
import ru.mirea.clientserverapps.serverbackend.models.User;

import java.util.List;

public interface TrayService {
    public abstract void addToTray(User user, Product p, int count);
    public abstract void removeFromTray(User user, int id);
    public abstract List<Product> getTrayContent(User user);
}
