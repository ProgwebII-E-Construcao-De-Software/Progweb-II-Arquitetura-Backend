package br.ueg.progweb2.arquitetura.exceptions;
import lombok.Getter;

public @Getter class BusinessException extends RuntimeException {
    private ErrorValidation error;

    public BusinessException(ErrorValidation error) {
        super(error.getMessage());
        this.error = error;
    }
}