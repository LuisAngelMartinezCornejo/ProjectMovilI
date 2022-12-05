package com.example.proyectomovil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ConfirmacionViaje extends AppCompatActivity {

    TextView idViaje, destino, transporte, fecha, dias, asientos, reservacionNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmacion_viaje);


        idViaje = (TextView) findViewById(R.id.txtIDViaje);
        destino = (TextView) findViewById(R.id.txtDestino);
        transporte = (TextView) findViewById(R.id.txtTransporte);
        fecha = (TextView) findViewById(R.id.txtFecha);
        dias = (TextView) findViewById(R.id.txtDias);
        asientos = (TextView) findViewById(R.id.txtAsientos);
        reservacionNombre = (TextView) findViewById(R.id.txtReservacion);

        idViaje.setText(String.valueOf(Comun.user.viajesUsuario.getId()));
        destino.setText(Comun.user.viajesUsuario.getLugar().getCiudad());
        transporte.setText(Comun.user.viajesUsuario.getTransporte().getTipo());
        fecha.setText(Comun.user.viajesUsuario.getFecha().toString());
        dias.setText(String.valueOf(Comun.user.viajesUsuario.getDiasEstancia()));
        asientos.setText(Comun.user.viajesUsuario.getAsientos());
        reservacionNombre.setText(Comun.user.viajesUsuario.getNombreReserva());

        Comun.MisViajes.add(Comun.user.viajesUsuario);
    }

    public void entendido(View view){
        finish();
    }
}