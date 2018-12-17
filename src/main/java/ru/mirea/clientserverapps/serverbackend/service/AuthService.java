package ru.mirea.clientserverapps.serverbackend.service;

import ru.mirea.clientserverapps.serverbackend.models.User;

public interface AuthService {
    public abstract boolean registerUser(String name, String balance, String hash);
    public abstract String authUser(String hash, String salt, String name);
    public abstract User checkToken(String AToken);
    public abstract String refreshToken(String RToken);

}
