package com.example.proyectomovil.interfaces;

public interface SeatsCallback {
    void onAnswerCompleted(int[] asientosOcupados);
    void onAnswerError(String errorMessage);
}
