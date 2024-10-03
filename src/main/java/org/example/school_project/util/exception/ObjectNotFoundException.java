package org.example.school_project.util.exception;

public class ObjectNotFoundException extends RuntimeException{
    public ObjectNotFoundException(String objectName) {
        super(objectName + " is not found");
    }
}
