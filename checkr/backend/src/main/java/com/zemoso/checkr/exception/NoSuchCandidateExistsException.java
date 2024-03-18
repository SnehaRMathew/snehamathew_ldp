package com.zemoso.checkr.exception;

public class NoSuchCandidateExistsException extends Throwable {
    long candidateId;
    public NoSuchCandidateExistsException(long candidateId){
        this.candidateId=candidateId;
    }

}
