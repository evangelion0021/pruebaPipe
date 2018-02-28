package com.prueba.votacion.controller.services;

import com.prueba.votacion.controller.services.interfaces.PersonaService;
import com.prueba.votacion.model.dao.interfaces.BaseDaoSpring;
import com.prueba.votacion.model.dao.interfaces.PersonaDao;
import com.prueba.votacion.model.dto.Persona;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
class PersonaServiceImpl extends ServiceImpl<Persona, Integer> implements PersonaService {

    private PersonaDao personaDao;

    @Autowired
    public PersonaServiceImpl(@Qualifier("personaDaoImpl") BaseDaoSpring<Persona, Integer> dao) {
        super(dao);
        this.personaDao = (PersonaDao) dao;
    }
}
