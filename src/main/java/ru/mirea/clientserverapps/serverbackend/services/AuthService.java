package ru.mirea.clientserverapps.serverbackend.services;

import ru.mirea.clientserverapps.serverbackend.exceptions.AuthFailedException;
import ru.mirea.clientserverapps.serverbackend.exceptions.TokenOutOfDateException;
import ru.mirea.clientserverapps.serverbackend.exceptions.UserAlreadyExistsException;
import ru.mirea.clientserverapps.serverbackend.models.User;

public interface AuthService {
    public abstract void registerUser(String name, String balance, String hash) throws UserAlreadyExistsException;
    public abstract String authUser(String hash, String salt, String name) throws AuthFailedException;
    public abstract User checkToken(String AToken) throws TokenOutOfDateException, AuthFailedException;
    public abstract String refreshToken(String RToken) throws TokenOutOfDateException, AuthFailedException;

}
