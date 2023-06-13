package com.example.proyectomovil.interfaces;

import com.example.proyectomovil.Usuario;

public interface RegisterSeatCallback {
    void onAnswerCompleted(boolean completado);
    void onAnswerError(String errorMessage);
}
