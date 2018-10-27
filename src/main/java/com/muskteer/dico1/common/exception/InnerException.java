package com.muskteer.dico1.common.exception;

public class InnerException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public InnerException() {
        super();
    }

    public InnerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public InnerException(String message, Throwable cause) {
        super(message, cause);
    }

    public InnerException(String message) {
        super(message);
    }

    public InnerException(Throwable cause) {
        super(cause);
    }

}
