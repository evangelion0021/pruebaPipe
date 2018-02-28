package com.prueba.votacion.controller.restservices;

import com.prueba.votacion.controller.exceptions.PersonaNotFoundException;
import com.prueba.votacion.controller.services.interfaces.PersonaService;
import com.prueba.votacion.controller.util.security.UserValidator;
import com.prueba.votacion.model.dto.Persona;
import java.net.URI;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/{userId}/personas")
public class PersonaRestController {

    @Autowired(required = true)
    @Qualifier(value = "personaServiceImpl")
    PersonaService personaService;
    UserValidator userValidator = new UserValidator();

    @Transactional
    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> add(@PathVariable String userId, @RequestBody Persona persona) {
        userValidator.validateUser(userId);
        persona = this.personaService.merge(persona);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{cc}").buildAndExpand(persona.getCc()).toUri();
        return ResponseEntity.created(location).build();
    }

    @Transactional
    @RequestMapping(method = RequestMethod.PUT)
    ResponseEntity<?> update(@PathVariable String userId, @RequestBody Persona persona) {
        userValidator.validateUser(userId);
        persona = this.personaService.merge(persona);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{cc}").buildAndExpand(persona.getCc()).toUri();
        return ResponseEntity.created(location).build();
    }

    @RequestMapping(method = RequestMethod.GET)
    Collection<Persona> list(@PathVariable String userId) {
        userValidator.validateUser(userId);
        return this.personaService.list();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{cc}")
    Persona findPersona(@PathVariable String userId, @PathVariable int cc) {
        userValidator.validateUser(userId);
        Persona persona = this.personaService.searchById(cc);
        if (persona == null) {
            throw new PersonaNotFoundException(cc);
        }
        return persona;
    }

    @Transactional
    @RequestMapping(method = RequestMethod.DELETE, value = "/{cc}")
    Persona deletePersona(@PathVariable String userId, @PathVariable int cc) {
        userValidator.validateUser(userId);
        Persona persona = this.personaService.searchById(cc);
        if (persona == null) {
            throw new PersonaNotFoundException(cc);
        }
        this.personaService.delete(persona);
        return persona;
    }
}
