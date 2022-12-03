package com.example.proyectomovil;

public class Lugar {
    private int id;
    private String ciudad;
    private String estado;
    private String pais;
    private boolean disponible;

    public Lugar(int id, String ciudad, String estado, String pais, boolean disponible) {
        this.id = id;
        this.ciudad = ciudad;
        this.estado = estado;
        this.pais = pais;
        this.disponible = disponible;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}
