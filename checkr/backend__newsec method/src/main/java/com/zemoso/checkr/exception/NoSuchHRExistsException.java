package com.zemoso.checkr.exception;

public class NoSuchHRExistsException extends Exception {
    long hrID;
    public NoSuchHRExistsException(long hrID){
        this.hrID=hrID;
    }

}
