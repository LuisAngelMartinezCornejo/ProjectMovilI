package com.example.proyectomovil.interfaces;

import org.json.JSONObject;

public interface VolleyCallback
{
    void onSuccess(JSONObject response);
    void onError(String errorMessage);
}