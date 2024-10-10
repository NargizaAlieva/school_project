package org.example.school_project.util.exception;

public class AlreadyExistException extends RuntimeException{
    public AlreadyExistException(String object, String parameter) {
        super(object + " with that " + parameter + " is already exist.");
    }
}
