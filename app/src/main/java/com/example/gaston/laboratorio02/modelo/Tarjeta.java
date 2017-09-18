package com.example.gaston.laboratorio02.modelo;

import java.util.Date;

/**
 * Created by fede_ on 12/09/2017.
 */

public class Tarjeta implements java.io.Serializable{

    private String nombre;
    private Integer numero;
    private Integer seguridad;
    private Date fechaVencimiento;

    public Tarjeta(String nombre, Integer numero, Integer seguridad, Date fechaVencimiento) {
        this.nombre = nombre;
        this.numero = numero;
        this.seguridad = seguridad;
        this.fechaVencimiento = fechaVencimiento;
    }

    @Override
    public String toString() {
        return "Tarjeta{" +
                "nombre='" + nombre + '\'' +
                ", numero=" + numero +
                ", seguridad=" + seguridad +
                ", fechaVencimiento=" + fechaVencimiento +
                '}';
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getSeguridad() {
        return seguridad;
    }

    public void setSeguridad(Integer seguridad) {
        this.seguridad = seguridad;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }
}
