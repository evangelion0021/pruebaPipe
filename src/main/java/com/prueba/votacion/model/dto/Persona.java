package com.prueba.votacion.model.dto;
// Generated 27/02/2018 07:18:48 PM by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Persona generated by hbm2java
 */
@Entity
@Table(name = "persona",
         catalog = "votacion"
)

public class Persona implements java.io.Serializable {

    @Id
    @Column(name = "cc", unique = true, nullable = false)
    private int cc;
    @Column(name = "nombre", nullable = false, length = 45)
    private String nombre;
    @Column(name = "telefono", nullable = false, length = 10)
    private String telefono;
    @Column(name = "direccion", nullable = false, length = 25)
    private String direccion;
    @Column(name = "barrio", nullable = false, length = 25)
    private String barrio;
    private boolean esadmin;
    @Column(name = "mesa", nullable = false)
    private int mesa;

    public Persona() {
    }

    public Persona(int cc, String nombre, String telefono, String direccion, String barrio, boolean esadmin, int mesa) {
        this.cc = cc;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.barrio = barrio;
        this.esadmin = esadmin;
        this.mesa = mesa;
    }

    public int getCc() {
        return this.cc;
    }

    public void setCc(int cc) {
        this.cc = cc;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getBarrio() {
        return this.barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    @Column(name = "esadmin", nullable = false)
    public boolean isEsadmin() {
        return this.esadmin;
    }

    public void setEsadmin(boolean esadmin) {
        this.esadmin = esadmin;
    }

    public int getMesa() {
        return this.mesa;
    }

    public void setMesa(int mesa) {
        this.mesa = mesa;
    }

}
