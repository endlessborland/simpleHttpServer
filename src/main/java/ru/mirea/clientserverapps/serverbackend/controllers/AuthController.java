package ru.mirea.clientserverapps.serverbackend.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.mirea.clientserverapps.serverbackend.enums.StatusType;
import ru.mirea.clientserverapps.serverbackend.exceptions.AuthFailedException;
import ru.mirea.clientserverapps.serverbackend.exceptions.TokenOutOfDateException;
import ru.mirea.clientserverapps.serverbackend.exceptions.UserAlreadyExistsException;
import ru.mirea.clientserverapps.serverbackend.models.Response;
import ru.mirea.clientserverapps.serverbackend.models.User;
import ru.mirea.clientserverapps.serverbackend.services.AuthService;

@Controller
public class AuthController {

    @Autowired
    AuthService authService;

    /**
     * Registers user in the system
     * @param name Unique username
     * @param hash login:password hash. Unencrypted at the moment
     * @param balance BigDecimal User's balance
     * @return String success/fail message
     */
    @RequestMapping(value = "register", method = RequestMethod.POST)
    @ResponseBody
    public Response register(@RequestParam("name") String name, @RequestParam("hash") String hash, @RequestParam("balance") String balance)
    {
        try {
            authService.registerUser(name, balance, hash);
            return new Response(StatusType.OK, null);
        } catch (UserAlreadyExistsException e) {
            return new Response(StatusType.FAIL, e.toString());
        }

    }

    /**
     * Authenticates user in the system
     * @param name Username
     * @param hash Username's SHA256(SHA256(login:password) + salt)
     * @param salt Salt
     * @return Tokens of auth in a form of string: "authToken refreshToken"
     */
    @RequestMapping(value = "auth", method = RequestMethod.GET)
    @ResponseBody
    public Response auth(@RequestParam("name") String name, @RequestParam("hash") String hash, @RequestParam("salt") String salt)
    {
        try {
            return new Response(StatusType.OK, authService.authUser(hash, salt, name));
        } catch (AuthFailedException e) {
            return new Response(StatusType.FAIL, e.toString());
        }
    }

    /**
     * Refershes tokens
     * @param rToken Refresh token
     * @return Tokens of auth in a form of string: "authToken refreshToken"
     */
    @RequestMapping(value = "refresh", method = RequestMethod.GET)
    @ResponseBody
    public Response refresh(@RequestParam("rtoken") String rToken)
    {
        try {
            User user = authService.checkToken(rToken);
            return new Response(StatusType.OK, authService.refreshToken(rToken));
        } catch (TokenOutOfDateException e) {
            return new Response(StatusType.FAIL, e.toString());
        } catch (AuthFailedException e) {
            return new Response(StatusType.FAIL, e.toString());
        }
    }
}