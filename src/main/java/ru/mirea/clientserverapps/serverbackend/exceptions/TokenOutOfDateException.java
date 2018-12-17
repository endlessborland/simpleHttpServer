package ru.mirea.clientserverapps.serverbackend.exceptions;

public class TokenOutOfDateException extends Exception {
    public TokenOutOfDateException() {};

    public String toString() {
        return "Token is out of date. Use RefreshToken to get new AccessToken";
    }
}
