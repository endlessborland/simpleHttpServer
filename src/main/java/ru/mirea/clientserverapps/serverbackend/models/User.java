package ru.mirea.clientserverapps.serverbackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import ru.mirea.clientserverapps.serverbackend.dao.UserDAO;

import java.math.BigDecimal;

public class User {

    private int userID;
    private String name;
    private BigDecimal balance;
    private String hash;

    @JsonIgnore
    private User user;

    @JsonIgnore
    @Autowired
    private UserDAO userDAO;

    public User(int userID, String name, String balance, String hash) {
        this.userID = userID;
        this.name = name;
        this.balance = new BigDecimal(balance);
        this.hash = hash;
        this.user = this.userDAO.getUser(this.userID);
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(String balance) {
        this.balance = new BigDecimal(balance);
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public int getUserID() {

        return userID;
    }

    public String getName() {
        return name;
    }

    public String getBalance() {
        return balance.toString();
    }

    public String getHash() {
        return hash;
    }
}
