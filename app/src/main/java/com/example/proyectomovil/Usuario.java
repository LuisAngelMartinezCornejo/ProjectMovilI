package com.example.proyectomovil;

import java.io.Serializable;
import java.util.Date;

public class Usuario implements Serializable {
    private int IDUser;

    public int getIDUser() {
        return IDUser;
    }

    public void setIDUser(int IDUser) {
        this.IDUser = IDUser;
    }

    private String nombre;
    private String correo;
    private long telefono;
    private String direccion;
    private String contraseña;

    public Viaje viajesUsuario = new Viaje();
    public int contadorViajes = 0;

    public Usuario(String nombre, String correo, long telefono, String direccion, String contraseña) {
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.direccion = direccion;
        this.contraseña = contraseña;
    }

    public Viaje getViajesUsuario() {
        return viajesUsuario;
    }

    public void setViajesUsuario(Viaje viajesUsuario) {
        this.viajesUsuario = viajesUsuario;
    }

    public Usuario() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    @Override
    public String toString()
    {
        return this.telefono + "\n" +
                "***" + this.contraseña + "\n" +
                "nombre:" + this.nombre + "\n" +
                "correo:" + this.correo + "\n" +
                "direccion:" + this.direccion + "\n";
    }
}
