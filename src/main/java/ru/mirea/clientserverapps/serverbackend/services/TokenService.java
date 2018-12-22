package ru.mirea.clientserverapps.serverbackend.services;

import ru.mirea.clientserverapps.serverbackend.models.Token;
import ru.mirea.clientserverapps.serverbackend.models.User;

public interface TokenService {
    public abstract String createJWT(User user, long durationDays);
    public abstract Token verifyToken(String token);
}
