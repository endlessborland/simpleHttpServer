package ru.mirea.clientserverapps.serverbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.mirea.clientserverapps.serverbackend.enums.StatusType;
import ru.mirea.clientserverapps.serverbackend.exceptions.AuthFailedException;
import ru.mirea.clientserverapps.serverbackend.exceptions.IDNotFoundException;
import ru.mirea.clientserverapps.serverbackend.exceptions.NotEnoughInstancesException;
import ru.mirea.clientserverapps.serverbackend.exceptions.TokenOutOfDateException;
import ru.mirea.clientserverapps.serverbackend.models.Response;
import ru.mirea.clientserverapps.serverbackend.models.User;
import ru.mirea.clientserverapps.serverbackend.services.AuthService;
import ru.mirea.clientserverapps.serverbackend.services.TrayService;

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
            return new Response(StatusType.OK, trayService.getTrayContent(user));
        } catch (TokenOutOfDateException e) {
            return new Response(StatusType.FAIL, e.toString());
        } catch (AuthFailedException e) {
            return new Response(StatusType.FAIL, e.toString());
        }
    }

    @RequestMapping(value = "tray", method = RequestMethod.PUT)
    @ResponseBody
    public Response checkout(@RequestParam("token") String token)
    {
        try {
            User user = authService.checkToken(token);
            trayService.checkout(user);
            return new Response(StatusType.OK, null);
        } catch (IDNotFoundException e) {
            return new Response(StatusType.FAIL, e.toString());
        } catch (NotEnoughInstancesException e) {
            return new Response(StatusType.FAIL, e.toString());
        } catch (TokenOutOfDateException e) {
            return new Response(StatusType.FAIL, e.toString());
        } catch (AuthFailedException e) {
            return new Response(StatusType.FAIL, e.toString());
        }
    }

    @RequestMapping(value = "tray/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Response delete(@PathVariable int id, @RequestParam("token") String token)
    {
        try {
            User user = authService.checkToken(token);
            trayService.removeFromTray(user, id);
            return new Response(StatusType.OK, null);
        } catch (TokenOutOfDateException e) {
            return new Response(StatusType.FAIL, e.toString());
        } catch (IDNotFoundException e) {
            return new Response(StatusType.FAIL, e.toString());
        } catch (AuthFailedException e) {
            return new Response(StatusType.FAIL, e.toString());
        }
    }
}
