package br.ueg.progweb2.arquitetura.validations;


import java.util.List;

public interface IValidations<MODEL> {
    void validate(MODEL data, ValidationAction action);
    List<ValidationAction> getActions();
}
