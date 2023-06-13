package com.example.proyectomovil;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Button;
import java.io.Serializable;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class PantallaViaje extends AppCompatActivity {

    Viaje viaje;
    Viaje auxViaje;
    Date fechaViaje;
    TextView destino, id, transporte, cantidadDias, cantidadBoletos, fecha;
    private Intent intent;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_viaje);

        intent = getIntent();
        usuario = (Usuario) getIntent().getSerializableExtra("usuario");
        viaje  = usuario.getViajesUsuario();

        destino = (TextView) findViewById(R.id.txtDestinoInfo);
        id = (TextView) findViewById(R.id.txtIDInfo);
        transporte = (TextView) findViewById(R.id.txtTransporteInfo);
        cantidadDias = (TextView) findViewById(R.id.txtCantDiasInfo);
        cantidadBoletos = (TextView) findViewById(R.id.txtCantBoletosInfo);
        fecha = (TextView) findViewById(R.id.txtFechaInfo);

        destino.setText(String.valueOf(viaje.getLugar().getCiudad()));
        transporte.setText(viaje.getTransporte().getTipo());
        id.setText(String.valueOf(viaje.getId()));
    }

    public void fecha (View view) {
        Calendar horarioHoy = Calendar.getInstance();

        int anioActual = horarioHoy.get(Calendar.YEAR);
        int mesActual = horarioHoy.get(Calendar.MONTH);
        int diaActual = horarioHoy.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(PantallaViaje.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                String fechaSeleccionada = i2 + " / " + (i1 + 1) + " / " + i;

                Calendar auxCalendar = Calendar.getInstance();
                auxCalendar.set(Calendar.YEAR, i);
                auxCalendar.set(Calendar.MONTH, i1);
                auxCalendar.set(Calendar.DAY_OF_MONTH, i2);

                fechaViaje = auxCalendar.getTime();
                fecha.setText(fechaSeleccionada);
            }
        }, anioActual, mesActual, diaActual);
        datePickerDialog.setTitle("Fecha de cita");
        datePickerDialog.show();
    }

    public void siguiente(View view){
        if (!transporte.getText().toString().isEmpty() && !cantidadDias.getText().toString().isEmpty() && !cantidadBoletos.getText().toString().isEmpty() && !fecha.getText().toString().isEmpty()) {

            auxViaje = new Viaje(
                    viaje.getId(),
                    viaje.getLugar(),
                    viaje.getTransporte(),
                    fechaViaje,
                    Integer.parseInt(cantidadDias.getText().toString()),
                    cantidadBoletos.toString(),
                    viaje.getNombreReserva());


            Intent intent = new Intent(this, SeleccionAsientos.class);
            intent.putExtra("viaje", auxViaje);
            intent.putExtra("usuario", usuario);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Llena todos los campos.", Toast.LENGTH_SHORT).show();
        }
    }
}