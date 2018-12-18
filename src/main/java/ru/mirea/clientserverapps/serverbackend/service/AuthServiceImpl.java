package ru.mirea.clientserverapps.serverbackend.service;

import com.google.common.hash.Hashing;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.mirea.clientserverapps.serverbackend.dao.TrayDAO;
import ru.mirea.clientserverapps.serverbackend.dao.UserDAO;
import ru.mirea.clientserverapps.serverbackend.exceptions.TokenOutOfDateException;
import ru.mirea.clientserverapps.serverbackend.models.Token;
import ru.mirea.clientserverapps.serverbackend.models.User;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;


@Service
public class AuthServiceImpl implements AuthService {

    private final Long ACCESS_TOKEN_DURATION = 1L;
    private final Long REFRESH_TOKEN_DURATION = 31L;


    @Autowired
    UserDAO userDAO;

    @Autowired
    AuthHelper authHelper;

    @Autowired
    TrayDAO trayDAO;

    @Override
    public boolean registerUser(String name, String balance, String hash) {
        if (userDAO.doesUserExist(name))
            return false;
        this.userDAO.addUser(name, new BigDecimal(balance), hash);
        // register a tray for user
        this.trayDAO.createTray(userDAO.getUser(name));
        return true;
    }


    // creating a user
    @Override
    public String authUser(String hash, String salt, String name) {
        User user = userDAO.getUser(name);
        if (user == null)
            return "No user registered with this Name";
        String hashed = Hashing.sha256().hashString(user.getHash() + salt, StandardCharsets.UTF_8).toString();
        if (hashed.equals(hash)) {
            String accessToken = authHelper.createJWT(user, ACCESS_TOKEN_DURATION);
            String refreshToken = authHelper.createJWT(user, REFRESH_TOKEN_DURATION);
            return accessToken + " " + refreshToken;
        }
        return null;
    }

    @Override
    public User checkToken(String AToken) throws TokenOutOfDateException{
        Token t = authHelper.verifyToken(AToken);
        if (t == null)
            return null;
        // uhm... yeah, time's a bit tricky. hope this monster works
        if (t.getExpires().isAfter(new DateTime(new org.joda.time.Instant(Calendar.getInstance().getTimeInMillis()))))
            throw new TokenOutOfDateException();
        return userDAO.getUser(t.getUserId());
    }

    @Override
    public String refreshToken(String RToken) throws TokenOutOfDateException {
        Token t = authHelper.verifyToken(RToken);
        if (t == null)
            return "Refresh token is invalid";
        User user = this.userDAO.getUser(t.getUserId());
        String accessToken = authHelper.createJWT(user, ACCESS_TOKEN_DURATION);
        String refreshToken = authHelper.createJWT(user, REFRESH_TOKEN_DURATION);
        return accessToken + " " + refreshToken;
    }
}
