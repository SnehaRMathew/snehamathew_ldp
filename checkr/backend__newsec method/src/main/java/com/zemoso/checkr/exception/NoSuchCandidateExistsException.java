package com.zemoso.checkr.exception;

public class NoSuchCandidateExistsException extends Exception {
    long candidateId;
    public NoSuchCandidateExistsException(long candidateId){
        this.candidateId=candidateId;
    }

}
