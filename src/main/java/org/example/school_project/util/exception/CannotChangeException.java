package org.example.school_project.util.exception;

public class CannotChangeException extends RuntimeException{
    public CannotChangeException(String parameter) {
        super("You cannot change " + parameter);
    }
}
