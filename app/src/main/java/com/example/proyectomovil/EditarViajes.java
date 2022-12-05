package com.example.proyectomovil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class EditarViajes extends AppCompatActivity {

    ArrayList<Viaje> viajes = new ArrayList<>();
    TextView showViaje;
    EditText IDViaje;

    int datoABuscar;
    boolean found = false;
    int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_viajes);

        viajes = Comun.MisViajes;
        showViaje = (TextView) findViewById(R.id.showViaje);
        IDViaje = (EditText) findViewById(R.id.edtCodigo);
    }

    public void buscar(View view){
        if(!IDViaje.getText().toString().isEmpty()){
            datoABuscar = Integer.parseInt(IDViaje.getText().toString());

            for (int i = 0; i < viajes.size(); i++){
                if(viajes.get(i).getId() == datoABuscar){
                    showViaje.setText(viajes.get(i).toString());
                    index = i;
                    found = true;
                    return;
                }
            }
            found = false   ;
            Toast.makeText(this, "No se encontró", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Ingresa un código", Toast.LENGTH_SHORT).show();
        }
    }


    public void cancelarViaje(View view) {
        if(found){
            viajes.remove(index);
            Comun.MisViajes = viajes;
            Toast.makeText(this, "Viaje removido >:)", Toast.LENGTH_SHORT).show();
            found = false;
            index = 0;

            Intent intent = new Intent(this, MisViajes.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Selecciona un ID que exista >:(", Toast.LENGTH_SHORT).show();
        }
    }
}