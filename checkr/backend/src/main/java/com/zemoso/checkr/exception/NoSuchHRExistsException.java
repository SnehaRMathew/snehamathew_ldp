package com.zemoso.checkr.exception;

public class NoSuchHRExistsException extends Exception {
    long candidateId;
    public NoSuchHRExistsException(long candidateId){
        this.candidateId=candidateId;
    }

}
