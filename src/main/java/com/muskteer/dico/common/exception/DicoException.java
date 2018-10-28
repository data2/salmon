package com.muskteer.dico.common.exception;

public class DicoException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public DicoException() {
        super();
    }

    public DicoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public DicoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DicoException(String message) {
        super(message);
    }

    public DicoException(Throwable cause) {
        super(cause);
    }
}
