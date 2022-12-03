package com.example.proyectomovil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import java.io.Serializable;

public class PantallaViaje extends AppCompatActivity {
    Button btn ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_viaje);
        btn = findViewById(R.id.btnSiguiente);
        btn.setOnClickListener(view->{
            Intent i =  new Intent(this, SeleccionAsientos.class);
            startActivity(i);
        });
    }
}