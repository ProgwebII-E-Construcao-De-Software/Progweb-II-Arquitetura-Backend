package br.ueg.progweb2.exampleuse.exceptions;
import br.ueg.progweb2.arquitetura.exceptions.MessageCode;
import lombok.Getter;

@Getter
public enum ErrorValidation implements MessageCode {


    GENERAL("ER500", 400,"Undefined error!"),
    INVALID_ID("ER400", 400,"Invalid Id!"),
    NOT_FOUND("ER404", 400,"Not found!"),
    MANDATORY_FIELD_VIOLATION("ER400", 400,"Mandatory field must be filled up!"),
    USER_ALREADY_EXISTS("ER405",400,"User already exists"),
    BUSINESS_LOGIC_VIOLATION("ER407",400,"Business logic violation!");

    private String code;
    private final Integer status;
    private String message;

    ErrorValidation(String code, Integer status, String message){
        this.code = code;
        this.status = status;
        this.message = message;
    }

    @Override
    public Integer getStatus() {
        return 0;
    }
}