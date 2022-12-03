package com.example.proyectomovil;

public class Asiento extends Transporte{

    private int ID;
    private int numero;
    private int fila;
    private int columna;
    private boolean disponible;

    public Asiento(String tipo, String empresa, String marca, String registro, String conductor, int ID, int numero, int fila, int columna, boolean disponible) {
        super(tipo, empresa, marca, registro, conductor);
        this.ID = ID;
        this.numero = numero;
        this.fila = fila;
        this.columna = columna;
        this.disponible = disponible;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}
