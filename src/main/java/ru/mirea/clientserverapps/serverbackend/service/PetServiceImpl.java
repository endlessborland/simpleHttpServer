package ru.mirea.clientserverapps.serverbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mirea.clientserverapps.serverbackend.dao.TrayDAO;
import ru.mirea.clientserverapps.serverbackend.models.PetWrapper;
import ru.mirea.clientserverapps.serverbackend.dao.PetDAO;
import ru.mirea.clientserverapps.serverbackend.exceptions.IDNotFoundException;
import ru.mirea.clientserverapps.serverbackend.exceptions.NotEnoughInstancesException;
import ru.mirea.clientserverapps.serverbackend.models.Pet;
import ru.mirea.clientserverapps.serverbackend.models.ProductTrayWrapper;
import ru.mirea.clientserverapps.serverbackend.models.User;

import java.util.List;

@Service
public class PetServiceImpl implements PetService {
    @Autowired
    private PetDAO petDAO;

    @Autowired
    private TrayDAO trayDAO;

    @Override
    public Pet getPet(int id) throws IDNotFoundException {
        Pet pet = petDAO.getPet(id);
        if (pet == null)
            throw new IDNotFoundException(id);
        return pet;
    }

    @Override
    public List<PetWrapper> getPets() {
        return petDAO.getPets();
    }

    @Override
    public void buyPet(int id, int amount, User user) throws IDNotFoundException, NotEnoughInstancesException {
        Pet pet = petDAO.getPet(id);
        if (pet == null)
            throw new IDNotFoundException(id);
        if (pet.getCount() < amount)
            throw new NotEnoughInstancesException();
        petDAO.buyPet(id, pet.getCount() - amount);
        // working with trayDAO
        // try to update amount field in future?
        trayDAO.addToCart(user, new ProductTrayWrapper(pet.getId(), amount, "TypePet"));
    }
}
