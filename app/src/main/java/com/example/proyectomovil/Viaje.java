package com.example.proyectomovil;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Viaje implements Serializable {
    private static final long serialVersionUID = 123456789L;
    private int idTripAux;
    private int id;
    private Lugar lugar;
    private Transporte transporte;
    private Date fecha;
    private int diasEstancia;
    private String asientos;
    private String nombreReserva;

    public Viaje () {
    }

    public Viaje (int id, String ciudad, String fecha) {
        this.id = id;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        try {
            this.fecha = dateFormat.parse(fecha);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Lugar lugarAux = new Lugar();
        lugarAux.setCiudad(ciudad);
        this.lugar = lugarAux;
    }

    public Viaje (int idTrip, String ciudad, int id, String fecha, String asientos) {
        this.id = id;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        try {
            this.fecha = dateFormat.parse(fecha);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Lugar lugarAux = new Lugar();
        lugarAux.setCiudad(ciudad);
        this.lugar = lugarAux;
        this.asientos = asientos;
        this.idTripAux = idTrip;
    }

    public Viaje(int id, String fecha, String ciudad, String transporte)
    {
        this.id = id;

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        try {
            this.fecha = dateFormat.parse(fecha);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Lugar lugarAux = new Lugar();
        lugarAux.setCiudad(ciudad);
        this.lugar = lugarAux;

        Transporte transporteAux = new Transporte();
        transporteAux.setTipo(transporte);
        this.transporte = transporteAux;
    }

    public Viaje(int id, Lugar lugar, Transporte transporte, Date fecha, int diasEstancia, String asientos, String nombreReserva) {
        this.id = id;
        this.lugar = lugar;
        this.transporte = transporte;
        this.fecha = fecha;
        this.diasEstancia = diasEstancia;
        this.asientos = asientos;
        this.nombreReserva = nombreReserva;
    }

    /*
    @Override
    public String toString()
    {
        return  "Lugar: " + this.lugar.getCiudad() + "\n" +
                "Transporte: " + this.transporte.getTipo() + "\n" +
                "Fecha: " + this.fecha + "\n" +
                "Dias de estancia:" + this.diasEstancia + "\n" +
                "Asientos: " + this.asientos + "\n" +
                "A nombre de: " + this.nombreReserva;
    }
     */

    @Override
    public String toString()
    {
        return  "Lugar: " + this.lugar.getCiudad() + "\n" +
                "Fecha: " + this.fecha + "\n" +
                "ID: " + this.id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Lugar getLugar() {
        return lugar;
    }

    public void setLugar(Lugar lugar) {
        this.lugar = lugar;
    }

    public Transporte getTransporte() {
        return transporte;
    }

    public void setTransporte(Transporte transporte) {
        this.transporte = transporte;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getDiasEstancia() {
        return diasEstancia;
    }

    public void setDiasEstancia(int diasEstancia) {
        this.diasEstancia = diasEstancia;
    }

    public String getAsientos() {
        return asientos;
    }

    public void setAsientos(String asientos) {
        this.asientos = asientos;
    }

    public String getNombreReserva() {
        return nombreReserva;
    }

    public void setNombreReserva(String nombreReserva) {
        this.nombreReserva = nombreReserva;
    }

    public int getIdTripAux() {
        return idTripAux;
    }

    public void setIdTripAux(int idTripAux) {
        this.idTripAux = idTripAux;
    }
}
