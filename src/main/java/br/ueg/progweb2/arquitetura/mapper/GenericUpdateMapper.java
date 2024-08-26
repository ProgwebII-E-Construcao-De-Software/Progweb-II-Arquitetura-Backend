package br.ueg.progweb2.arquitetura.mapper;

import br.ueg.progweb2.arquitetura.model.GenericModel;
import org.mapstruct.MappingTarget;

public interface GenericUpdateMapper<
        MODEL extends GenericModel<TYPE_PK>,
        TYPE_PK
        > {
    void updateModelFromModel(@MappingTarget MODEL entity, MODEL updateEntity);
}
