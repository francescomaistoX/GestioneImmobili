package it.maisto.GestoneImmobili.exception;

public class LoginFaultException extends RuntimeException{
    private String message;
    public LoginFaultException(String message){
        super(message);
    }
}
