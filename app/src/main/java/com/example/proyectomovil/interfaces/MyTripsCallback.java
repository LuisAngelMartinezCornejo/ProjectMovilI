package com.example.proyectomovil.interfaces;

import com.example.proyectomovil.Viaje;

import java.util.ArrayList;

public interface MyTripsCallback {
    void onAnswerCompleted(ArrayList<Viaje> misViajes);
    void onAnswerError(String errorMessage);
}
