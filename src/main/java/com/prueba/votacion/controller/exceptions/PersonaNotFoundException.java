package com.prueba.votacion.controller.exceptions;

public class PersonaNotFoundException extends RuntimeException {

    public PersonaNotFoundException(int cc) {
        super("No se encuentra el Persona '" + cc + "'.");
    }
}
