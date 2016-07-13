package com.luxoft.training.solid.store;

import java.sql.SQLException;

public class PersistenceException extends RuntimeException {

    public PersistenceException(Exception e) {
        super(e);
    }

    public PersistenceException(String message) {
        super(message);
    }
}
