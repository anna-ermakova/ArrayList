package org.example.exception;

public class ListIndexOutOfBoundsException extends RuntimeException {
    public ListIndexOutOfBoundsException() {
        super();
    }

    public ListIndexOutOfBoundsException(String massage) {
        super(massage);
    }
}