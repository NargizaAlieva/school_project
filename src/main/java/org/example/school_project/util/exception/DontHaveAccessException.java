package org.example.school_project.util.exception;

public class DontHaveAccessException extends RuntimeException{
    public DontHaveAccessException() {
        super("You cannot do that because you don't have access.");
    }
}
