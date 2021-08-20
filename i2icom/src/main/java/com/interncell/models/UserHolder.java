package com.interncell.models;

public final class UserHolder {

    private User user;
    private final static UserHolder INSTANCE = new UserHolder();

    private UserHolder()
    {

    }

    public static UserHolder getInstance() {
        return INSTANCE;
    }

    public void setUser(User user)
    {
        this.user = user;
    }
    
    public User getUser()
    {
        return this.user;
    }

}
