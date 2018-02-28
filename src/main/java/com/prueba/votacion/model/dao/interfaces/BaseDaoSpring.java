package com.prueba.votacion.model.dao.interfaces;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import java.util.Map;

/**
 * * * @param <T> Tipo de Objeto del que será la DAO * @param <E> Tipo de dato
 * del id por el que se buscará un objeto
 */
public interface BaseDaoSpring<T extends Serializable, E> {

    public void actualizar(final T instance);

    public void actualizarVarios(final Collection<T> instances);

    public void borrarVarios(Collection<T> instances);

    public int actualizarPorSentencia(final String query, final Map<String, Object> parametros);

    public E guardar(T instance);

    public void replicar(final T instance);

    public void guardarOActualizarVarios(Collection<T> instances);

    public void guardarVarios(final Collection<T> instances);

    public void guardarOActualizar(T instance);

    public void persistir(T transientInstance);

    public void attachDirty(T instance);

    public void attachClean(T instance);

    public void borrar(T persistentInstance);

    public List<T> buscarPorObjeto(T instance);

    public T buscarPorConsulta(final String queryString, final Map<String, Object> parametros);

    public List<T> buscarListadoPorConsulta(final String query, final Map<String, Object> parametros);

    public List<T> buscarPorCriteria(DetachedCriteria criteria);

    public T buscarUnicoPorCriteria(final DetachedCriteria criteria);

    public T mezclar(T detachedInstance);

    public List<T> mezclarVarios(final Collection<T> detachedInstances);

    public List<T> listarTodo();

    public T buscarPorId(E id);

    public List<T> buscarVariosPorListaDeAtributos(final Collection<E> ids, final Class clase, final String nombreAtributo);

    public T buscarUnicoPorAtributo(final Object atributo, final Class clase, final String nombreAtributo);

    public List<T> buscarVariosPorAtributo(final Object atributo, final Class clase, final String nombreAtributo);

    @Deprecated
    public T buscarUnicoPorAtributos(Map<String, Object> atributos);

    public void persistirVarios(Collection<T> instances);
}
