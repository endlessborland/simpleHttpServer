package ru.mirea.clientserverapps.serverbackend.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.mirea.clientserverapps.serverbackend.exceptions.TokenOutOfDateException;
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
    public String register(@RequestParam("name") String name, @RequestParam("hash") String hash, @RequestParam("balance") String balance)
    {
        if (authService.registerUser(name, balance, hash))
            return "Register successfull";
        return "Register failed";
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
    public String auth(@RequestParam("name") String name, @RequestParam("hash") String hash, @RequestParam("salt") String salt)
    {
        return this.authService.authUser(hash, salt, name);
    }

    /**
     * Refershes tokens
     * @param rToken Refresh token
     * @return Tokens of auth in a form of string: "authToken refreshToken"
     */
    @RequestMapping(value = "refresh", method = RequestMethod.GET)
    @ResponseBody
    public String refresh(@RequestParam("rtoken") String rToken)
    {
        try {
            User user = authService.checkToken(rToken);
            if (user == null)
                return "Token invalid";
            return this.authService.refreshToken(rToken);
        } catch (TokenOutOfDateException e) {
            return "Please authenticate";
        }
    }
}