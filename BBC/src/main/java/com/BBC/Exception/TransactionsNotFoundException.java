package com.BBC.Exception;

public class TransactionsNotFoundException  extends RuntimeException {
    public TransactionsNotFoundException(String message) {
        super(message);
    }
}