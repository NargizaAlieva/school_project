package org.example.school_project.util.exception;

public class WasDeletedException extends RuntimeException{
    public WasDeletedException(String objectName) {
        super("This " + objectName + " was deleted");
    }

}
