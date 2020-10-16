package com.rms.kalah.error.exception;

import java.io.Serial;

public class InvalidGameIdException extends Exception {

    @Serial
    private static final long serialVersionUID = 2939046489821109583L;

    public InvalidGameIdException(String message) {
        super(message);
    }

}
