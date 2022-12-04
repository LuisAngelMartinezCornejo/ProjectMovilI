package com.example.proyectomovil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import java.io.Serializable;


public class PantallaViaje extends AppCompatActivity {

    Usuario user;
    Viaje viaje;

    TextView destino, id, transporte, cantidadDias, cantidadBoletos, fecha;

    Button btn ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_viaje);

        user = (Usuario) getIntent().getSerializableExtra("user");
        viaje = (Viaje) getIntent().getSerializableExtra("viaje");

        destino = (TextView) findViewById(R.id.txtDestinoInfo);
        id = (TextView) findViewById(R.id.txtIDInfo);
        transporte = (TextView) findViewById(R.id.txtTransporteInfo);
        cantidadDias = (TextView) findViewById(R.id.txtCantDiasInfo);
        cantidadBoletos = (TextView) findViewById(R.id.txtCantidadBoletos);
        fecha = (TextView) findViewById(R.id.txtFechaInfo);

        destino.setText(viaje.getLugar().getCiudad());
        id.setText(viaje.getId());
        transporte.setText(viaje.getTransporte().getTipo());
        cantidadDias.setText(viaje.getDiasEstancia());
        cantidadBoletos.setText(viaje.getAsientos());
        fecha.setText(viaje.getFecha().toString());
    }

    public void siguiente(View view){
        Intent intent = new Intent(this, SeleccionAsientos.class);
        intent.putExtra("user", user);
        intent.putExtra("viaje", viaje);
        startActivity(intent);
        finish();
        btn = findViewById(R.id.btnSiguiente);
        btn.setOnClickListener(view->{
            Intent i =  new Intent(this, SeleccionAsientos.class);
            startActivity(i);
        });
    }
}