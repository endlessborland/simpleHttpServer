package ru.mirea.clientserverapps.serverbackend;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.mirea.clientserverapps.serverbackend.service.AuthService;

import java.util.ArrayList;
import java.util.List;

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
}
