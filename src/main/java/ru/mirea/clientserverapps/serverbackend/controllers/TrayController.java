package ru.mirea.clientserverapps.serverbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.mirea.clientserverapps.serverbackend.dao.TrayDAO;
import ru.mirea.clientserverapps.serverbackend.enums.ItemType;
import ru.mirea.clientserverapps.serverbackend.enums.StatusType;
import ru.mirea.clientserverapps.serverbackend.exceptions.IDNotFoundException;
import ru.mirea.clientserverapps.serverbackend.exceptions.NotEnoughInstancesException;
import ru.mirea.clientserverapps.serverbackend.exceptions.TokenOutOfDateException;
import ru.mirea.clientserverapps.serverbackend.models.Product;
import ru.mirea.clientserverapps.serverbackend.models.ProductTrayWrapper;
import ru.mirea.clientserverapps.serverbackend.models.Response;
import ru.mirea.clientserverapps.serverbackend.models.User;
import ru.mirea.clientserverapps.serverbackend.services.AuthService;
import ru.mirea.clientserverapps.serverbackend.services.TrayService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TrayController {

    @Autowired
    private TrayService trayService;

    @Autowired
    private AuthService authService;

    @RequestMapping(value = "tray", method = RequestMethod.GET)
    @ResponseBody
    public Response tray(@RequestParam("token") String token)
    {
        try {
            User user = authService.checkToken(token);
            if (user == null)
                return new Response(StatusType.FAIL, "Auth failed");
            return new Response(StatusType.OK, trayService.getTrayContent(user));
        } catch (TokenOutOfDateException e) {
            return new Response(StatusType.FAIL, e.toString());
        }
    }

    @RequestMapping(value = "tray", method = RequestMethod.PUT)
    @ResponseBody
    public Response checkout(@RequestParam("token") String token)
    {
        try {
            User user = authService.checkToken(token);
            if (user == null)
                return new Response(StatusType.FAIL, "Auth failed");
            trayService.checkout(user);
        } catch (IDNotFoundException e) {
            return new Response(StatusType.FAIL, e.toString());
        } catch (NotEnoughInstancesException e) {
            return new Response(StatusType.FAIL, e.toString());
        } catch (TokenOutOfDateException e) {
            return new Response(StatusType.FAIL, e.toString());
        }
        return new Response(StatusType.OK, null);
    }

    @RequestMapping(value = "tray/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Response delete(@PathVariable int id, @RequestParam("token") String token)
    {
        try {
            User user = authService.checkToken(token);
            if (user == null)
                return new Response(StatusType.FAIL, "Auth failed");
            trayService.removeFromTray(user, id);
            return new Response(StatusType.OK, null);
        } catch (TokenOutOfDateException e) {
            return new Response(StatusType.FAIL, e.toString());
        } catch (IDNotFoundException e) {
            return new Response(StatusType.FAIL, e.toString());
        }
    }
}
