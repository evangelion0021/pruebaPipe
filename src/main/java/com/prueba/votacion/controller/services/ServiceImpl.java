package com.prueba.votacion.controller.services;

import com.prueba.votacion.model.dao.interfaces.BaseDaoSpring;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.prueba.votacion.controller.services.interfaces.BaseService;
import org.springframework.stereotype.Service;


abstract class ServiceImpl<T extends Serializable, E> implements BaseService<T, E> {

    private BaseDaoSpring<T, E> dao;

    public ServiceImpl(BaseDaoSpring<T, E> dao) {
        this.dao = dao;
    }

    public ServiceImpl() {
    }

    public void setDao(BaseDaoSpring dao) {
        this.dao = dao;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<T> list() {
        return dao.listarTodo();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void update(T instance) {
        dao.actualizar(instance);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateCollection(Collection<T> instances) {
        dao.actualizarVarios(instances);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteCollection(Collection<T> instances) {
        dao.borrarVarios(instances);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public E save(T instance) {
        return (E) dao.guardar(instance);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveOrUpdateCollection(Collection<T> instances) {
        dao.guardarOActualizarVarios(instances);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveCollection(Collection<T> instances) {
        dao.guardarVarios(instances);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveOrUpdate(T instance) {
        dao.guardarOActualizar(instance);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void persist(T transientInstance) {
        dao.persistir(transientInstance);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void persistCollection(Collection<T> instances) {
        dao.persistirVarios(instances);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(final T persistentInstance) {
        dao.borrar(persistentInstance);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public T merge(final T detachedInstance) {
        return (T) dao.mezclar(detachedInstance);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<T> mergeCollection(final Collection<T> detachedInstances) {
        return dao.mezclarVarios(detachedInstances);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public T searchById(E id) {
        return (T) dao.buscarPorId(id);
    }
}
