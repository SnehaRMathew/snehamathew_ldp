package com.zemoso.checkr.exception;

import java.util.NoSuchElementException;

public class NoSuchChargeExistsException extends NoSuchElementException {
    long chageId;
    public NoSuchChargeExistsException(Long chargeId) {
        this.chageId=chargeId;
    }
}
