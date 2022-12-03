package com.example.proyectomovil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ConfirmacionViaje extends AppCompatActivity {

    Usuario user;
    Viaje viaje;

    TextView idViaje, destino, transporte, fecha, dias, asientos, reservacionNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmacion_viaje);

        user = (Usuario) getIntent().getSerializableExtra("user");
        viaje = (Viaje) getIntent().getSerializableExtra("viaje");

        idViaje = (TextView) findViewById(R.id.txtIDViaje);
        destino = (TextView) findViewById(R.id.txtDestino);
        transporte = (TextView) findViewById(R.id.txtTransporte);
        fecha = (TextView) findViewById(R.id.txtFecha);
        dias = (TextView) findViewById(R.id.txtDias);
        asientos = (TextView) findViewById(R.id.txtAsientos);
        reservacionNombre = (TextView) findViewById(R.id.txtReservacion);

        idViaje.setText(viaje.getId());
        destino.setText(viaje.getLugar().getCiudad());
        transporte.setText(viaje.getTransporte().getTipo());
        fecha.setText(viaje.getFecha().toString());
        dias.setText(viaje.getDiasEstancia());
        asientos.setText(viaje.getAsientos());
        reservacionNombre.setText(viaje.getNombreReserva());
    }

    public void entendido(View view){
        user.viajesUsuario[user.contadorViajes] = viaje;
        user.contadorViajes++;

        Intent intent = new Intent(this, MenuPrincipal.class);
        intent.putExtra("user", user);
        startActivity(intent);
        finish();
    }
}