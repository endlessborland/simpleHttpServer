package ru.mirea.clientserverapps.serverbackend.models;

public class Session {
    private int ID;
    private int UserID;
    private String AToken;
    private String RToken;

    public Session(int ID, int userID, String AToken, String RToken) {
        this.ID = ID;
        UserID = userID;
        this.AToken = AToken;
        this.RToken = RToken;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public void setAToken(String AToken) {
        this.AToken = AToken;
    }

    public void setRToken(String RToken) {
        this.RToken = RToken;
    }

    public int getID() {

        return ID;
    }

    public int getUserID() {
        return UserID;
    }

    public String getAToken() {
        return AToken;
    }

    public String getRToken() {
        return RToken;
    }

}
