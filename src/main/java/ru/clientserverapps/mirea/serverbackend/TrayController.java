package ru.clientserverapps.mirea.serverbackend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.clientserverapps.mirea.serverbackend.domain.Product;
import ru.clientserverapps.mirea.serverbackend.domain.Tray;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class TrayController {
    @RequestMapping(value = "tray", method = RequestMethod.GET)
    @ResponseBody
    public List<Product> tray()
    {
        return Tray.shoppingList;
    }

    @RequestMapping(value = "tray", method = RequestMethod.PUT)
    @ResponseBody
    BigDecimal checkout()
    {
        BigDecimal a = new BigDecimal("0");
        for (Product item : Tray.shoppingList)
        {
            // ?
            a = a.add(item.getPrice());
        }
        Tray.shoppingList.clear();
        return a;
    }

    @RequestMapping(value = "tray/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    void delete(@PathVariable int id)
    {
        Tray.deleteFromCart(id);
    }
}
