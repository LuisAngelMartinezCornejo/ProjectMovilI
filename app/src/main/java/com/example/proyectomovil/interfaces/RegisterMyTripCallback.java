package com.example.proyectomovil.interfaces;

import com.example.proyectomovil.Viaje;

import java.util.ArrayList;

public interface RegisterMyTripCallback {
    void onAnswerCompleted(boolean completado);
    void onAnswerError(String errorMessage);
}
