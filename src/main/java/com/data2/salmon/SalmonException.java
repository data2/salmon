package com.data2.salmon;

/**
 * @author leewow
 */
public class SalmonException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public SalmonException() {
        super();
    }

    public SalmonException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public SalmonException(String message, Throwable cause) {
        super(message, cause);
    }

    public SalmonException(String message) {
        super(message);
    }

    public SalmonException(Throwable cause) {
        super(cause);
    }
}
