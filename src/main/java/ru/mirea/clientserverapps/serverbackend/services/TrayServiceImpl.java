package ru.mirea.clientserverapps.serverbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mirea.clientserverapps.serverbackend.dao.PetDAO;
import ru.mirea.clientserverapps.serverbackend.dao.StuffDAO;
import ru.mirea.clientserverapps.serverbackend.dao.TrayDAO;
import ru.mirea.clientserverapps.serverbackend.dao.UserDAO;
import ru.mirea.clientserverapps.serverbackend.enums.ItemType;
import ru.mirea.clientserverapps.serverbackend.exceptions.IDNotFoundException;
import ru.mirea.clientserverapps.serverbackend.exceptions.NotEnoughInstancesException;
import ru.mirea.clientserverapps.serverbackend.models.ProductTrayWrapper;
import ru.mirea.clientserverapps.serverbackend.models.User;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TrayServiceImpl implements TrayService{
    @Autowired
    private TrayDAO trayDAO;

    @Autowired
    private PetDAO petDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private StuffDAO stuffDAO;

    public TrayServiceImpl() {
    }

    @Override
    public void removeFromTray(User user, int id) throws IDNotFoundException {
        this.trayDAO.removeFromCart(user, id);
    }

    @Override
    public List<ProductTrayWrapper> getTrayContent(User user) {
        return trayDAO.getTrayItems(user);
    }

    @Override
    public void checkout(User user) throws IDNotFoundException, NotEnoughInstancesException {
        BigDecimal price = new BigDecimal("0");
        List<ProductTrayWrapper> productTrayWrappers = trayDAO.getTrayItems(user);
        for (ProductTrayWrapper a : productTrayWrappers) {
            if (a.getItemType().equals(ItemType.TypePet.toString()))
            {
                String p = petDAO.getPet(trayDAO.getItem(user, a.getId()).getProductID()).getPrice();
                BigDecimal dbg = new BigDecimal(p);
                price = price.add(new BigDecimal(p));
                price = price.multiply(new BigDecimal(a.getCount()));
            }
            if (a.getItemType().equals(ItemType.TypeStuff.toString()))
            {
                String p = stuffDAO.getStuff(trayDAO.getItem(user, a.getId()).getProductID()).getPrice();
                BigDecimal dbg = new BigDecimal(p);
                price = price.add(new BigDecimal(p));
                price = price.multiply(new BigDecimal(a.getCount()));
            }
        }
        if (new BigDecimal(user.getBalance()).compareTo(price) >= 0)
            userDAO.alterBalance(user, (new BigDecimal(user.getBalance()).subtract(price)).toString());
        else
            throw new NotEnoughInstancesException();
        trayDAO.emptyTray(user);
    }
}
