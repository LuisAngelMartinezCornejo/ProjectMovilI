package com.example.proyectomovil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import java.io.Serializable;
import java.util.ArrayList;


public class PantallaViaje extends AppCompatActivity {

    Usuario user;
    ArrayList<Viaje> viajes  = new ArrayList<>();
    Viaje viaje;

    TextView destino, id, transporte, cantidadDias, cantidadBoletos, fecha;

    Button btn ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_viaje);

        user = Comun.user;
        viaje  = user.getViajesUsuario();

        destino = (TextView) findViewById(R.id.txtDestinoInfo);
        id = (TextView) findViewById(R.id.txtIDInfo);
        transporte = (TextView) findViewById(R.id.txtTransporteInfo);
        cantidadDias = (TextView) findViewById(R.id.txtCantDiasInfo);
        cantidadBoletos = (TextView) findViewById(R.id.txtCantBoletosInfo);
        fecha = (TextView) findViewById(R.id.txtFechaInfo);

        destino.setText(String.valueOf(viaje.getLugar().getCiudad()));
        id.setText(String.valueOf(viaje.getId()));
        transporte.setText(viaje.getTransporte().getTipo());
        cantidadDias.setText(String.valueOf(viaje.getDiasEstancia()));
        cantidadBoletos.setText(String.valueOf(viaje.getAsientos()));
        fecha.setText(viaje.getFecha().toString());
    }

    public void siguiente(View view){
        Intent intent = new Intent(this, SeleccionAsientos.class);
        startActivity(intent);
        finish();
        btn = findViewById(R.id.btnSiguiente);
        btn.setOnClickListener(View->{
            Intent i =  new Intent(this, SeleccionAsientos.class);
            startActivity(i);
        });
    }
}