package ru.mirea.clientserverapps.serverbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.mirea.clientserverapps.serverbackend.exceptions.IDNotFoundException;
import ru.mirea.clientserverapps.serverbackend.models.Pet;
import ru.mirea.clientserverapps.serverbackend.models.PetWrapper;
import ru.mirea.clientserverapps.serverbackend.services.AuthService;
import ru.mirea.clientserverapps.serverbackend.services.PetService;

import java.util.List;

@Controller
public class PetController {

    @Autowired
    private PetService petService;

    @Autowired
    private AuthService authService;

    @RequestMapping(value = "pet", method = RequestMethod.GET)
    @ResponseBody
    public List<PetWrapper> pets()
    {
        return petService.getPets();
    }

    @RequestMapping(value = "pet/{id}", method = RequestMethod.GET)
    @ResponseBody
    @Transactional
    public Pet pet(@PathVariable int id)
    {
        try {
            return petService.getPet(id);
        } catch (IDNotFoundException e)
        {
            return null;
        }
    }

    @RequestMapping(value = "pet/{id}/buy", method = RequestMethod.GET)
    @ResponseBody
    public void buyPet(@PathVariable int id, @RequestParam("token") String token)
    {

    }
}
