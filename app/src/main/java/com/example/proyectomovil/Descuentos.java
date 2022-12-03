package com.example.proyectomovil;

import java.util.Date;

public class Descuentos extends Usuario{
    private int ID;
    private int cantidadViajes;
    private int tipoDescuento;
    private String cantidadDescuento;
    private Date fechaLimite;

    public Descuentos(String nombre, String correo, int telefono, String direccion, String contraseña, int ID, int cantidadViajes, int tipoDescuento, String cantidadDescuento, Date fechaLimite) {
        super(nombre, correo, telefono, direccion, contraseña);
        this.ID = ID;
        this.cantidadViajes = cantidadViajes;
        this.tipoDescuento = tipoDescuento;
        this.cantidadDescuento = cantidadDescuento;
        this.fechaLimite = fechaLimite;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getCantidadViajes() {
        return cantidadViajes;
    }

    public void setCantidadViajes(int cantidadViajes) {
        this.cantidadViajes = cantidadViajes;
    }

    public int getTipoDescuento() {
        return tipoDescuento;
    }

    public void setTipoDescuento(int tipoDescuento) {
        this.tipoDescuento = tipoDescuento;
    }

    public String getCantidadDescuento() {
        return cantidadDescuento;
    }

    public void setCantidadDescuento(String cantidadDescuento) {
        this.cantidadDescuento = cantidadDescuento;
    }

    public Date getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(Date fechaLimite) {
        this.fechaLimite = fechaLimite;
    }
}
