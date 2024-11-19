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
    MODEL fromModeltoDTO(DTO dto);
    MODEL fromDTOCreateToModel(DTOCreate dtoCreate);

    MODEL fromDTOUpdateToModel(DTOUpdate dtoUpdate);



    DTO fromModeltoDTO(MODEL model);

    @Named(value = "toDTOList") // para identificar o nome desse metodo pelo mapstruct
    DTOList toDTOList(MODEL model);

    @IterableMapping(qualifiedByName = "toDTOList") // para orientar qual metodo utilizar no caso de vários target=source;
    List<DTOList> fromModelToDTOList(List<MODEL> modelList);

    MODEL fromModelCreatedToModel(DTOCreate dto);

    DTO toDTO(MODEL model);

    MODEL fromModelUpdatedToModel(DTOUpdate dto);
}
