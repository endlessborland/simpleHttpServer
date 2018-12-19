package ru.mirea.clientserverapps.serverbackend.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.mirea.clientserverapps.serverbackend.models.Stuff;

import java.util.ArrayList;
import java.util.List;

@Controller
public class StuffController {

    @RequestMapping(value = "stuff", method = RequestMethod.GET)
    @ResponseBody
    public List<Stuff> stuffz()
    {
        return new ArrayList<>();
    }

    @RequestMapping(value = "stuff/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Stuff stuff(@PathVariable int id)
    {
        return new Stuff(1,"test", "test", "1", 1);
    }

    @RequestMapping(value = "stuff/{id}/buy", method = RequestMethod.GET)
    @ResponseBody
    public void buy(@PathVariable int id)
    {


    }
}
