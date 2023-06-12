package com.example.proyectomovil;

import java.io.Serializable;

public class Transporte implements Serializable {
    private String tipo;
    private String empresa;
    private String marca;
    private String registro;
    private String conductor;

    public Transporte(){}

    public Transporte(String tipo, String empresa, String marca, String registro, String conductor) {
        this.tipo = tipo;
        this.empresa = empresa;
        this.marca = marca;
        this.registro = registro;
        this.conductor = conductor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }

    public String getConductor() {
        return conductor;
    }

    public void setConductor(String conductor) {
        this.conductor = conductor;
    }
}
