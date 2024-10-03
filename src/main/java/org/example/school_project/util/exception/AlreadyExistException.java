package org.example.school_project.util.exception;

public class AlreadyExistException extends RuntimeException{
    public AlreadyExistException(String parameter) {
        super("User with that + " + parameter + " is already exist.");
    }
}
