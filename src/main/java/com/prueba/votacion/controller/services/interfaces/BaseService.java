package com.prueba.votacion.controller.services.interfaces;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public interface BaseService<T extends Serializable, E> {

    public void update(final T instance);

    public void updateCollection(final Collection<T> instances);

    public void deleteCollection(Collection<T> instances);

    public E save(T instance);

    public void saveOrUpdateCollection(Collection<T> instances);

    public void saveCollection(final Collection<T> instances);

    public void saveOrUpdate(T instance);

    public void persist(T transientInstance);

    public void persistCollection(Collection<T> instances);

    public void delete(T persistentInstance);

    public T merge(T detachedInstance);

    public List<T> mergeCollection(final Collection<T> detachedInstances);

    public List<T> list();

    public T searchById(E id);
}
