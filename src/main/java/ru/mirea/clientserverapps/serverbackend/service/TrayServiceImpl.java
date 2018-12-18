package ru.mirea.clientserverapps.serverbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.mirea.clientserverapps.serverbackend.dao.TrayDAO;
import ru.mirea.clientserverapps.serverbackend.models.Product;
import ru.mirea.clientserverapps.serverbackend.models.User;

import java.util.List;

public class TrayServiceImpl implements TrayService{
    @Autowired
    private TrayDAO trayDAO;

    @Override
    public void addToTray(User user, Product p, int count) {

    }

    @Override
    public void removeFromTray(User user, int id) {

    }

    @Override
    public List<Product> getTrayContent(User user) {
        return null;
    }
}
