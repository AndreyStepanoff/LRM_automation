package io.github.sskorol.raid.exceptions;

public class NoSuchElementException extends RuntimeException {

    public NoSuchElementException(final String message) {
        super(message);
    }

    public NoSuchElementException(final String message, final Throwable throwable) {
        super(message, throwable);
    }
}
