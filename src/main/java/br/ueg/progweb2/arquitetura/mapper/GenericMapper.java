package br.ueg.progweb2.arquitetura.mapper;

import br.ueg.progweb2.arquitetura.model.GenericModel;
import org.mapstruct.IterableMapping;
import org.mapstruct.Named;

import java.util.List;

public interface GenericMapper<
        DTO,
        DTOCreate,
        DTOUpdate,
        DTOList,
        MODEL extends GenericModel<TYPE_PK>,
        TYPE_PK
        > extends GenericUpdateMapper<MODEL, TYPE_PK> {

    MODEL fromDTOtoModel(DTO dto);
    MODEL fromDTOCreateToModel(DTOCreate dtoCreate);

    MODEL fromDTOUpdateToModel(DTOUpdate dtoUpdate);



    DTO fromModeltoDTO(MODEL model);

    @Named(value = "toDTOList")
    DTOList toDTOList(MODEL model);

    @IterableMapping(qualifiedByName = "toDTOList")
    List<DTOList> fromModelToDTOList(List<MODEL> modelList);
}
