package org.example.school_project.util.exception;

public class IncorrectRequestException extends RuntimeException {
    public IncorrectRequestException(String parameter) {
        super("You need to provide " + parameter);
    }
}
