package ru.mirea.clientserverapps.serverbackend.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mirea.clientserverapps.serverbackend.dao.StuffDAO;
import ru.mirea.clientserverapps.serverbackend.dao.TrayDAO;
import ru.mirea.clientserverapps.serverbackend.exceptions.IDNotFoundException;
import ru.mirea.clientserverapps.serverbackend.exceptions.NotEnoughInstancesException;
import ru.mirea.clientserverapps.serverbackend.models.ProductTrayWrapper;
import ru.mirea.clientserverapps.serverbackend.models.Stuff;
import ru.mirea.clientserverapps.serverbackend.models.User;

import java.util.List;

@Service
public class StuffServiceImpl implements StuffService{
    @Autowired
    private StuffDAO stuffDAO;

    @Autowired
    private TrayDAO trayDAO;

    @Override
    public Stuff getStuff(int id) throws IDNotFoundException {
        Stuff stuff = stuffDAO.getStuff(id);
        if (stuff == null)
            throw new IDNotFoundException(id);
        return stuff;
    }

    @Override
    public List<Stuff> getStuffs() {
        return stuffDAO.getStuff();
    }

    @Override
    public void buyStuff(int id, int amount, User user) throws IDNotFoundException, NotEnoughInstancesException {
        Stuff stuff = stuffDAO.getStuff(id);
        if (stuff == null)
            throw new IDNotFoundException(id);
        if (stuff.getCount() < amount)
            throw new NotEnoughInstancesException();
        stuffDAO.buyStuff(id, stuff.getCount() - amount);
        // working with trayDAO
        ProductTrayWrapper productTrayWrapper = new ProductTrayWrapper(stuff.getId(), amount, "TypeStuff");
        if (trayDAO.isInCart(user, productTrayWrapper))
            trayDAO.alterProductAmount(user, productTrayWrapper);
        else
            trayDAO.addToCart(user, new ProductTrayWrapper(stuff.getId(), amount, "TypeStuff"));
    }
}
