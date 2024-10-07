package br.ueg.progweb2.arquitetura.exceptions;
import lombok.Getter;

public @Getter class BusinessException extends RuntimeException {
    private ErrorValidation error;

    public BusinessException(ErrorValidation error) {
        super(error.getMessage());
        this.error = error;
    }
    public BusinessException(String message, ErrorValidation error) {
        super(message + error.getMessage());
        this.error = error;
    }
}