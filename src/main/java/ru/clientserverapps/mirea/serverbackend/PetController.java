package ru.clientserverapps.mirea.serverbackend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.clientserverapps.mirea.serverbackend.domain.Data;
import ru.clientserverapps.mirea.serverbackend.domain.Pet;
import ru.clientserverapps.mirea.serverbackend.domain.Tray;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PetController {

    @RequestMapping(value = "pet", method = RequestMethod.GET)
    @ResponseBody
    public List<PetWrapper> pets()
    {
        List<PetWrapper> list = new ArrayList<PetWrapper>();
        for (Pet item : Data.petList)
        {
            list.add(new PetWrapper(item));
        }
        return list;
    }

    @RequestMapping(value = "pet/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Pet pet(@PathVariable int id)
    {
        if (id > Data.petList.size() || id < 0)
            return null;
        return Data.petList.get(id);
    }

    @RequestMapping(value = "pet/{id}/buy", method = RequestMethod.GET)
    @ResponseBody
    public void buyPet(@PathVariable int id)
    {
        if (id > Data.petList.size() || id < 0)
            return;
        Tray.addToCart(Data.petList.get(id));
    }
}
