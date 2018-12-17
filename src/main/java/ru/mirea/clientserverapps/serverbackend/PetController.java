package ru.mirea.clientserverapps.serverbackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.mirea.clientserverapps.serverbackend.dao.PetDAO;
import ru.mirea.clientserverapps.serverbackend.models.Pet;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PetController {

    @Autowired
    private PetDAO petDAO;

    @RequestMapping(value = "pet", method = RequestMethod.GET)
    @ResponseBody
    public List<PetWrapper> pets()
    {
        return new ArrayList<>();
    }

    @RequestMapping(value = "pet/{id}", method = RequestMethod.GET)
    @ResponseBody
    @Transactional
    public Pet pet(@PathVariable int id)
    {
        return this.petDAO.getPet(id);
    }

    @RequestMapping(value = "pet/{id}/buy", method = RequestMethod.GET)
    @ResponseBody
    public void buyPet(@PathVariable int id)
    {

    }
}
