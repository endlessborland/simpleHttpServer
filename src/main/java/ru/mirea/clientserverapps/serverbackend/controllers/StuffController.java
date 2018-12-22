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
import ru.mirea.clientserverapps.serverbackend.services.StuffService;

@Controller
public class StuffController {

    @Autowired
    private StuffService stuffService;

    @Autowired
    private AuthService authService;

    /**
     * Gets a list of pets
     * @return list of pets
     */
    @RequestMapping(value = "stuff", method = RequestMethod.GET)
    @ResponseBody
    public Response pets()
    {
        return new Response(StatusType.OK, stuffService.getStuffs());
    }


    /**
     * Gets specific info about a pet
     * @param id pet it
     * @return pet info
     */
    @RequestMapping(value = "stuff/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Response pet(@PathVariable int id)
    {
        try {
            return new Response(StatusType.OK, stuffService.getStuff(id));
        } catch (IDNotFoundException e)
        {
            return new Response(StatusType.FAIL, null);
        }
    }

    /**
     * Adds to cart a specific amount of a pet
     * @param id pet id
     * @param amount amount id
     * @param token user's token
     * @return Status
     */
    @RequestMapping(value = "stuff/{id}/buy", method = RequestMethod.GET)
    @ResponseBody
    public Response buyPet(@PathVariable int id, @RequestParam("amount") int amount, @RequestParam("token") String token)
    {
        try {
            User user = authService.checkToken(token);
            stuffService.buyStuff(id, amount, user);
            return new Response(StatusType.OK, null);
        } catch (TokenOutOfDateException e) {
            return new Response(StatusType.FAIL, e.toString());
        } catch (IDNotFoundException e) {
            return new Response(StatusType.FAIL, e.toString());
        } catch (NotEnoughInstancesException e) {
            return new Response(StatusType.FAIL, e.toString());
        } catch (AuthFailedException e) {
            return new Response(StatusType.FAIL, e.toString());
        }
    }
}
