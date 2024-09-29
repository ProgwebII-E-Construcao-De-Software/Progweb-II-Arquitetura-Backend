package br.ueg.progweb2.arquitetura.exceptions;

import lombok.Getter;

@Getter
public enum BusinessLogicError {
    GENERAL(0L, "Erro desconhecido!"),
    NOT_FOUND(404L, "Registro não encontrado!"),
    MANDATORY_FIELD_NOT_FOUND(1L, "Campo obrigatório não preenchido");
    private Long id;
    private String message;

    BusinessLogicError(Long id, String message){
        this.id = id;
        this.message = message;
    }
}
