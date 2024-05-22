package it.maisto.GestoneImmobili.exception;

public class UnAuthorizedException extends RuntimeException{
    public UnAuthorizedException(String message){
        super( message);
    }

}
