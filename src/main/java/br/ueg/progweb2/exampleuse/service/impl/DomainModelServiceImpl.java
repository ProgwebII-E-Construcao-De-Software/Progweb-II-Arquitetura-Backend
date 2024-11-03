package br.ueg.progweb2.exampleuse.service.impl;

import br.ueg.progweb2.arquitetura.service.impl.GenericCrudWithValidationsService;
import br.ueg.progweb2.exampleuse.model.DomainModel;
import br.ueg.progweb2.exampleuse.repository.DomainModelRespository;
import br.ueg.progweb2.exampleuse.service.DomainModelService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class DomainModelServiceImpl
        extends GenericCrudWithValidationsService<DomainModel, Long, DomainModelRespository>
        implements DomainModelService {
    @Override
    protected void validateBusinessToList(List<DomainModel> dataList) {

    }

    @Override
    protected void prepareToCreate(DomainModel dado) {
        if(Objects.nonNull(dado.getDescription())) {
            dado.setDescription(dado.getDescription().toUpperCase());
        }
    }

    @Override
    protected void validateBusinessLogicToCreate(DomainModel dado) {

    }

    @Override
    protected void validateBusinessToList(DomainModel data) {

    }

    @Override
    protected void prepareToUpdate(DomainModel newModel, DomainModel model) {

    }

    @Override
    protected void validateBusinessLogicToUpdate(DomainModel model) {

    }

    @Override
    protected void validateBusinessLogicToDelete(DomainModel model) {

    }

    @Override
    public List<DomainModel> listAllActives() {
        List<DomainModel> domainModels = this.listAll();
        return domainModels.stream().filter(DomainModel::getActive).toList();
    }

    @Override
    public Long removeAllInatives() {
        List<DomainModel> domainModelsInactives = this.listAll();
        domainModelsInactives = domainModelsInactives.stream().filter(domainModel -> !domainModel.getActive()).toList();
        Long total = (long) domainModelsInactives.size();
        this.repository.deleteAll(domainModelsInactives);
        return total;
    }
}
