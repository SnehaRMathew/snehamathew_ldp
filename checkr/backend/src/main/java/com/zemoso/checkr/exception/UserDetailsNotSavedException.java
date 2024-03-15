package com.zemoso.checkr.exception;

import com.zemoso.checkr.entity.User;

import java.util.logging.Logger;

public class UserDetailsNotSavedException extends Exception {
    Logger LOGGER = Logger.getLogger(UserDetailsNotSavedException.class.getName());
    User user;

    public UserDetailsNotSavedException(String message) {
        super(message);
        LOGGER.info( "HR details"+ user.toString());
    }

    public UserDetailsNotSavedException(User user) {
        this.user = user;
    }
}
