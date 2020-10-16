package com.rms.kalah.error.exception;

import java.io.Serial;

public class PitOutOfRangeException extends Exception {
    @Serial
    private static final long serialVersionUID = -6971722187284045839L;

    public PitOutOfRangeException(String message) {
        super(message);
    }

}
