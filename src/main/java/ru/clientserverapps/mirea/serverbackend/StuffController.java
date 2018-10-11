package ru.clientserverapps.mirea.serverbackend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.clientserverapps.mirea.serverbackend.domain.Data;
import ru.clientserverapps.mirea.serverbackend.domain.Stuff;
import ru.clientserverapps.mirea.serverbackend.domain.Tray;

import java.util.List;

@Controller
public class StuffController {

    @RequestMapping(value = "stuff", method = RequestMethod.GET)
    @ResponseBody
    public List<Stuff> stuffz()
    {
        return Data.stuffList;
    }

    @RequestMapping(value = "stuff/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Stuff stuff(@PathVariable int id)
    {
        if (id > Data.stuffList.size() || id < 0)
            return null;
        return Data.stuffList.get(id);
    }

    @RequestMapping(value = "stuff/{id}/buy", method = RequestMethod.GET)
    @ResponseBody
    public void buy(@PathVariable int id)
    {
        if (id > Data.stuffList.size() || id < 0)
            return;
        Tray.addToCart(Data.stuffList.get(id));
    }
}
