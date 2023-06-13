package com.example.proyectomovil.interfaces;

import com.example.proyectomovil.Usuario;

import java.util.ArrayList;

public interface UserCallback {
    void onAnswerCompleted(Usuario user);
    void onAnswerError(String errorMessage);
}
