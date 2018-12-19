package ru.mirea.clientserverapps.serverbackend.services;

import ru.mirea.clientserverapps.serverbackend.exceptions.TokenOutOfDateException;
import ru.mirea.clientserverapps.serverbackend.models.User;

public interface AuthService {
    public abstract boolean registerUser(String name, String balance, String hash);
    public abstract String authUser(String hash, String salt, String name);
    public abstract User checkToken(String AToken) throws TokenOutOfDateException;
    public abstract String refreshToken(String RToken) throws TokenOutOfDateException;

}
