package br.ueg.progweb2.arquitetura.exceptions;

import lombok.Getter;

@Getter
public enum BusinessLogicError {
    GENERAL(0L, "Erro desconhecido!"),
    REGISTER_NUMBER_DUPLICATED(100L, "Já existe"),
    REGISTER_NUMBER_INVALID(200L, "Registro inválido"),
    NOT_FOUND(404L, "Registro não encontrado!"),
    MANDATORY_FIELD_NOT_FOUND(1L, "Campo obrigatório não preenchido");
    private Long id;
    private String message;

    BusinessLogicError(Long id, String message){
        this.id = id;
        this.message = message;
    }
}
