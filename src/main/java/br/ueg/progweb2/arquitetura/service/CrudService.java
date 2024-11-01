package br.ueg.progweb2.arquitetura.service;

import br.ueg.progweb2.arquitetura.model.GenericModel;

import java.util.List;

public interface CrudService<
        MODEL extends GenericModel<TYPE_PK>, TYPE_PK
        > {

    List<MODEL> listAll();
    MODEL create(MODEL model);
    MODEL update(MODEL model);
    MODEL delete(TYPE_PK id);
    MODEL getById(TYPE_PK id);

    MODEL deleteById(TYPE_PK id);
    List<MODEL> deleteList(TYPE_PK[] ids);
}
