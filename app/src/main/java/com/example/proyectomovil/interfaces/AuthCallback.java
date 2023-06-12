package com.example.proyectomovil.interfaces;

import com.example.proyectomovil.Usuario;

public interface AuthCallback {
    void onUserAuthCompleted(Usuario usuario);
    void onAuthError(String errorMessage);
}
