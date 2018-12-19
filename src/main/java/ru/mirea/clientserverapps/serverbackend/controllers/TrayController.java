package ru.mirea.clientserverapps.serverbackend.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.mirea.clientserverapps.serverbackend.models.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TrayController {
    @RequestMapping(value = "tray", method = RequestMethod.GET)
    @ResponseBody
    public List<Product> tray()
    {
        return new ArrayList<>();
    }

    @RequestMapping(value = "tray", method = RequestMethod.PUT)
    @ResponseBody
    BigDecimal checkout()
    {
        return new BigDecimal("0");
    }

    @RequestMapping(value = "tray/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    void delete(@PathVariable int id)
    {

    }
}