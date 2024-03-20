package com.zemoso.checkr.exception;

import java.util.NoSuchElementException;

public class NoSuchChargeExistsException extends NoSuchElementException {
   final long chageId;
    public NoSuchChargeExistsException(Long chargeId) {
        this.chageId=chargeId;
    }
}
