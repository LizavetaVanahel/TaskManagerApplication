package com.example.vanahel.tasksmanagerapplication.events;

public class ExceptionMessageEvent {

    private final String message;

    public ExceptionMessageEvent( String message ) {
        this.message = message;
    }

    public String getExceptionMessage() {
        return message;
    }
}
