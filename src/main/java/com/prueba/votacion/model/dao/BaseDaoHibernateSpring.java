package com.prueba.votacion.model.dao;

import com.prueba.votacion.model.dao.interfaces.BaseDaoSpring;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import org.hibernate.Criteria;
import org.hibernate.query.Query;
import org.hibernate.ReplicationMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unchecked")
abstract class BaseDaoHibernateSpring<T extends Serializable, E extends Serializable> implements BaseDaoSpring<T, E> {

    @Autowired
    SessionFactory sessionFactory;
    protected Class<? extends E> daoType;
    final static Logger LOGGER = Logger.getLogger(BaseDaoHibernateSpring.class.getName());

    public BaseDaoHibernateSpring() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        daoType = (Class) pt.getActualTypeArguments()[0];
    }

    protected Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void actualizar(final T instance) {
        currentSession().update(instance);
    }

    @Override
    public void actualizarVarios(final Collection<T> instances) {
        for (T instance : instances) {
            currentSession().update(instance);
        }
    }

    @Override
    public void borrarVarios(final Collection<T> instances) {
        for (T instance : instances) {
            currentSession().delete(instance);
        }
    }

    @Override
    public int actualizarPorSentencia(final String sentencia, final Map<String, Object> parametros) {
        Query query;
        int filasAfectadas;
        query = currentSession().createQuery(sentencia);
        query.setProperties(parametros);
        filasAfectadas = query.executeUpdate();
        return filasAfectadas;
    }

    @Override
    public E guardar(final T instance) {
        return (E) currentSession().save(instance);
    }

    @Override
    public void replicar(final T instance) {
        currentSession().replicate(instance, ReplicationMode.EXCEPTION);
    }

    @Override
    public void guardarOActualizarVarios(final Collection<T> instances) {
        for (T instance : instances) {
            currentSession().saveOrUpdate(instance);
        }
    }

    @Deprecated
    @Override
    public void guardarVarios(final Collection<T> instances) {
        for (T instance : instances) {
            currentSession().persist(instance);
        }
    }

    @Override
    public void guardarOActualizar(final T instance) {
        currentSession().saveOrUpdate(instance);
    }

    @Override
    public void persistir(final T transientInstance) {
        currentSession().persist(transientInstance);
    }

    @Override
    public void persistirVarios(Collection<T> instances) {
        for (T instance : instances) {
            currentSession().persist(instance);
        }
    }

    @Override
    public void attachDirty(final T instance) {
        
    }

    @Override
    public void attachClean(final T instance) {
        
    }

    @Override
    public void borrar(final T persistentInstance) {
        currentSession().delete(persistentInstance);
    }

    @Override
    public T mezclar(final T detachedInstance) {
        return (T) currentSession().merge(detachedInstance);
    }

    @Override
    public List<T> mezclarVarios(final Collection<T> detachedInstances) {
        T result;
        List<T> results = new ArrayList<>(0);
        for (T detachedInstance : detachedInstances) {
            result = (T) currentSession().merge(detachedInstance);
            results.add(result);
        }
        return results;
    }

    @Override
    public List<T> buscarPorObjeto(final T instance) {
        return null;
    }

    @Override
    public T buscarPorConsulta(final String queryString, final Map<String, Object> parametros) {
        Query query = currentSession().createQuery(queryString);
        query.setMaxResults(1);
        if (parametros != null) {
            query.setProperties(parametros);
        }
        return (T) query.uniqueResult();
    }

    @Override
    public List<T> buscarListadoPorConsulta(final String queryString, final Map<String, Object> parametros) {
        Query query = currentSession().createQuery(queryString);
        if (parametros != null) {
            query.setProperties(parametros);
        }
        return (List<T>) query.list();
    }

    @Override
    public List<T> buscarPorCriteria(final DetachedCriteria criteria) {
        return (List<T>) criteria.getExecutableCriteria(currentSession()).list();
    }

    @Override
    public T buscarUnicoPorCriteria(final DetachedCriteria criteria) {
        return (T) criteria.getExecutableCriteria(currentSession()).uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> listarTodo() {
        Query query = currentSession().createQuery("SELECT e FROM " + daoType.getName() + " e");
        return (List<T>) query.list();
    }

    @Override
    public T buscarPorId(E id) {
        return (T) currentSession().get(daoType, (Serializable) id);
    }

    @Override
    public List<T> buscarVariosPorListaDeAtributos(final Collection<E> ids, final Class clase, final String nombreAtributo) {
        DetachedCriteria criteria = DetachedCriteria.forClass(clase);
        criteria.add(Restrictions.in(nombreAtributo, ids));
        return (List<T>) criteria.getExecutableCriteria(currentSession()).list();
    }

    @Override
    public T buscarUnicoPorAtributo(final Object atributo, final Class clase, final String nombreAtributo) {
        DetachedCriteria criteria = DetachedCriteria.forClass(clase);
        criteria.add(Restrictions.eq(nombreAtributo, atributo));
        return (T) criteria.getExecutableCriteria(currentSession()).uniqueResult();
    }

    @Override
    public List<T> buscarVariosPorAtributo(final Object atributo, final Class clase, final String nombreAtributo) {
        DetachedCriteria criteria = DetachedCriteria.forClass(clase);
        criteria.add(Restrictions.eq(nombreAtributo, atributo));
        return (List<T>) criteria.getExecutableCriteria(currentSession()).list();
    }

    /**
     * * Busca un objeto con el listado de parámetros definido * * @param
     * atributos Map con parámetros definidos del tipo * <nombreAtributo, valor>
     * * @return el objeto si existe o null si no * @throws BussinessException
     */
    @Override
    @Deprecated
    public T buscarUnicoPorAtributos(Map<String, Object> atributos) {
        Criteria criteria = currentSession().createCriteria(daoType);
        for (Map.Entry<String, Object> atributo : atributos.entrySet()) {
            criteria.add(Restrictions.eq(atributo.getKey(), atributo.getValue()));
        }
        return (T) criteria.uniqueResult();
    }
}
