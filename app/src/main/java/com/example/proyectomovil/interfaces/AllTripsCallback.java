package com.example.proyectomovil.interfaces;

import com.example.proyectomovil.Viaje;

import java.util.ArrayList;

public interface AllTripsCallback {
    void onAnswerCompleted(ArrayList<Viaje> viajes);
    void onAnswerError(String errorMessage);
}
