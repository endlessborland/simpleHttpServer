package ru.mirea.clientserverapps.serverbackend;


import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.mirea.clientserverapps.serverbackend.exceptions.TokenOutOfDateException;
import ru.mirea.clientserverapps.serverbackend.models.User;
import ru.mirea.clientserverapps.serverbackend.service.AuthService;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.hash.Hashing.sha256;

@Controller
public class AuthController {

    @Autowired
    AuthService authService;

    @RequestMapping(value = "register", method = RequestMethod.POST)
    @ResponseBody
    public String register(@RequestParam("name") String name, @RequestParam("hash") String hash, @RequestParam("balance") String balance)
    {
        if (authService.registerUser(name, balance, hash))
            return "Register successfull";
        return "Register failed";
    }

    @RequestMapping(value = "auth", method = RequestMethod.GET)
    @ResponseBody
    public String auth(@RequestParam("name") String name, @RequestParam("hash") String hash, @RequestParam("salt") String salt)
    {
        return this.authService.authUser(hash, salt, name);
    }

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