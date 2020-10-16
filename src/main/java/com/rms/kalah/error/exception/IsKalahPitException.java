package com.rms.kalah.error.exception;

import java.io.Serial;

public class IsKalahPitException extends Exception {
    @Serial
    private static final long serialVersionUID = 4927618780111401911L;

    public IsKalahPitException(String message) {
        super(message);
    }

}
