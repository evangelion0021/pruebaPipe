package com.prueba.votacion.model.dao;

import com.prueba.votacion.model.dao.interfaces.PersonaDao;
import com.prueba.votacion.model.dto.Persona;
import org.springframework.stereotype.Repository;

@Repository
class PersonaDaoImpl extends BaseDaoHibernateSpring<Persona, Integer> implements PersonaDao {
}
