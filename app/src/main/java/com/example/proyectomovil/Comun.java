package com.example.proyectomovil;

import java.util.ArrayList;

public class Comun {
    public static Usuario user = new Usuario();
    public static ArrayList<Viaje> MisViajes  = new ArrayList<>();

    public static ArrayList<Viaje> getMisViajes() {
        return MisViajes;
    }

    public static void setMisViajes(ArrayList<Viaje> misViajes) {
        MisViajes = misViajes;
    }
}
