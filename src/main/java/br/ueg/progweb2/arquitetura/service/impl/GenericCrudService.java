package br.ueg.progweb2.arquitetura.service.impl;

import br.ueg.progweb2.arquitetura.exceptions.BusinessException;
import br.ueg.progweb2.arquitetura.exceptions.ErrorValidation;
import br.ueg.progweb2.arquitetura.exceptions.MandatoryException;
import br.ueg.progweb2.arquitetura.mapper.GenericUpdateMapper;
import br.ueg.progweb2.arquitetura.model.GenericModel;
import br.ueg.progweb2.arquitetura.reflection.ModelReflection;
import br.ueg.progweb2.arquitetura.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional(propagation = Propagation.REQUIRED)
public abstract class GenericCrudService<
        MODEL extends GenericModel<TYPE_PK>,
        TYPE_PK,
        REPOSITORY extends JpaRepository<MODEL, TYPE_PK>
        > implements CrudService<
        MODEL,
        TYPE_PK
        > {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private GenericUpdateMapper<MODEL, TYPE_PK> mapper;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    protected REPOSITORY repository;

    public List<MODEL> getAll() {
        var modelList = repository.findAll();
        validateBusinessToList(modelList);
        return modelList;
    }

    public MODEL getById(TYPE_PK id) {
        return validateId(id);
    }

    public MODEL create(MODEL newModel) {
        validateMandatoryFields(newModel);
        validateBusinessLogicToCreate(newModel);
        prepareToCreate(newModel);
        return repository.save(newModel);
    }

    @Override
    public MODEL update(MODEL newModel) {
        validateMandatoryFields(newModel);
        validateBusinessLogicToUpdate(newModel);
        MODEL modelBD = validateId(newModel.getId());
        prepareToUpdate(newModel, modelBD);

        return repository.save(newModel);
    }

    @Override
    public MODEL delete(TYPE_PK id) {
        MODEL model = validateId(id);
        validateBusinessLogicToDelete(model);
        repository.deleteById(model.getId());
        return model;
    }

    protected MODEL validateId(TYPE_PK id) {

        Optional<MODEL> model = repository.findById(id);

        if (model.isEmpty()) {
            throw new BusinessException(ErrorValidation.INVALID_ID);
        }
        return model.get();
    }

    @Override
    public List<MODEL> deleteList(TYPE_PK[] ids) {
        List<MODEL> modelListDeleted = new ArrayList<>();
        for (TYPE_PK id : ids) {
            modelListDeleted.add(delete(id));
        }
        return modelListDeleted;
    }


    protected abstract void validateBusinessToList(List<MODEL> modelList);

    protected abstract void prepareToCreate(MODEL newModel);

    protected abstract void validateBusinessLogicToCreate(MODEL newModel);

    protected abstract void prepareToUpdate(MODEL newModel, MODEL model);

    protected abstract void validateBusinessLogicToUpdate(MODEL model);

    protected abstract void validateBusinessLogicToDelete(MODEL model);

    protected abstract void validateBusinessLogic(MODEL data);

    protected void validateMandatoryFields(MODEL data) {
        List<String> response = ModelReflection.getInvalidMandatoryFields(data);
        if (!response.isEmpty()) {
            throw new MandatoryException(response.toString());
        }
    }
}
