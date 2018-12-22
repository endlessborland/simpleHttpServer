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
import ru.mirea.clientserverapps.serverbackend.services.PetService;

@Controller
public class PetController {

    @Autowired
    private PetService petService;

    @Autowired
    private AuthService authService;

    /**
     * Gets a list of pets
     * @return list of pets
     */
    @RequestMapping(value = "pet", method = RequestMethod.GET)
    @ResponseBody
    public Response pets()
    {
        return new Response(StatusType.OK, petService.getPets());
    }


    /**
     * Gets specific info about a pet
     * @param id pet it
     * @return pet info
     */
    @RequestMapping(value = "pet/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Response pet(@PathVariable int id)
    {
        try {
            return new Response(StatusType.OK, petService.getPet(id));
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
    @RequestMapping(value = "pet/{id}/buy", method = RequestMethod.GET)
    @ResponseBody
    public Response buyPet(@PathVariable int id, @RequestParam("amount") int amount, @RequestParam("token") String token)
    {
        try {
            User user = authService.checkToken(token);
            petService.buyPet(id, amount, user);
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
