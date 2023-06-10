package com.example.proyectomovil.interfaces;

import com.example.proyectomovil.Viaje;

import java.util.ArrayList;

public interface RegisterUserCallback {
    void onAnswerCompleted(boolean resultado);
    void onAnswerError(String errorMessage);
}