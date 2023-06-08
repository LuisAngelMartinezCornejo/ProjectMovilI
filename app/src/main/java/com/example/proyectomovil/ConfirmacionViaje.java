package com.example.proyectomovil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ConfirmacionViaje extends AppCompatActivity {

    TextView idViaje, destino, transporte, fecha, dias, asientos, reservacionNombre;
    Viaje viaje;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmacion_viaje);

        intent = getIntent();
        viaje = (Viaje) intent.getSerializableExtra("viaje");

        idViaje = (TextView) findViewById(R.id.txtIDViaje);
        destino = (TextView) findViewById(R.id.txtDestino);
        transporte = (TextView) findViewById(R.id.txtTransporte);
        fecha = (TextView) findViewById(R.id.txtFecha);
        dias = (TextView) findViewById(R.id.txtDias);
        asientos = (TextView) findViewById(R.id.txtAsientos);
        reservacionNombre = (TextView) findViewById(R.id.txtReservacion);

        idViaje.setText(String.valueOf(viaje.getId()));
        destino.setText(viaje.getLugar().getCiudad());
        transporte.setText(viaje.getTransporte().getTipo());
        fecha.setText(viaje.getFecha().toString());
        dias.setText(String.valueOf(viaje.getDiasEstancia()));
        asientos.setText(viaje.getAsientos());
        reservacionNombre.setText(viaje.getNombreReserva());
    }

    // MANDAR A LA ACTIVITY DE DESCUENTO Y ENVIAR EL OBJETO TIPO VIAJE PARA EN DESCUENTOS ENVIAR EL VIAJE A LA BD
    public void entendido(View view){
        finish();
    }
}