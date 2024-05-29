package it.maisto.GestoneImmobili.exception;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorResponse extends Throwable {
    private String message;
    private LocalDateTime dataError;

    public ErrorResponse(String message) {
        this.message = message;
        dataError = LocalDateTime.now();
    }
}
