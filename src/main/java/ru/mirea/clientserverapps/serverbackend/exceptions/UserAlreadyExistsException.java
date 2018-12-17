package ru.mirea.clientserverapps.serverbackend.exceptions;

public class UserAlreadyExistsException extends Exception {
    public UserAlreadyExistsException() {
    }

    public String toString() {
        return "This UserName is already taken";
    }
}
