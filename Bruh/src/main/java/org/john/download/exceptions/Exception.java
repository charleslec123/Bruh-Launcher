package org.john.download.exceptions;

public class Exception extends RuntimeException {
    public Exception(String message) {
        super(message);
    }

    public Exception(Throwable cause) { super(cause); }

    public Exception(String message, Throwable cause) { super(message, cause); }

    public Exception() { super(); }
}
