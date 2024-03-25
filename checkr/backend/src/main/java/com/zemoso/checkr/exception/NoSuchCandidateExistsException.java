package com.zemoso.checkr.exception;

public class NoSuchCandidateExistsException extends RuntimeException {
    final long candidateId;
    public NoSuchCandidateExistsException(long candidateId){
        this.candidateId=candidateId;
    }

}
