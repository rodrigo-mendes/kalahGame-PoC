package com.rms.kalah.error.exception;

import java.io.Serial;

public class PitEmptyException extends Exception {
    @Serial
    private static final long serialVersionUID = -4265754589799628079L;

    public PitEmptyException(String message) {
        super(message);
    }

}
