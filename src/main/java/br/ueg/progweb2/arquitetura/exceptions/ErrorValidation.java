package br.ueg.progweb2.arquitetura.exceptions;

import lombok.Getter;

@Getter
public enum ErrorValidation {


    GENERAL("ER500", "Undefined error!"),
    INVALID_ID("ER400", "Invalid Id!"),
    NOT_FOUND("ER404", "Not found!"),
    MANDATORY_FIELD_VIOLATION("ER403", "Mandatory field must be filled up!"),
    USER_ALREADY_EXISTS("ER405","User already exists"),
    BUSINESS_LOGIC_VIOLATION("ER407","Business logic violation!");

    private String code;
    private String message;

    ErrorValidation( String code, String message){
        this.code = code;
        this.message = message;
    }
}