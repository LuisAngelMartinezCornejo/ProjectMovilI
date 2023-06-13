package com.example.proyectomovil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectomovil.interfaces.MyTripsCallback;
import com.example.proyectomovil.interfaces.RegisterSeatCallback;

import java.util.ArrayList;

public class EditarViajes extends AppCompatActivity {

    ArrayList<Viaje> viajes = new ArrayList<>();
    TextView showViaje;
    EditText IDViaje;
    int IDaux;
    private Usuario usuario;
    private Intent intent;

    int datoABuscar;
    boolean found = false;
    int index = 0;
    Viaje auxViaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_viajes);

        intent = getIntent();
        usuario = (Usuario) getIntent().getSerializableExtra("usuario");

        API.GET_My_Trips(usuario.getIDUser(), this, new MyTripsCallback() {
            @Override
            public void onAnswerCompleted(ArrayList<Viaje> misViajes) {
                viajes.addAll(misViajes);
            }

            @Override
            public void onAnswerError(String errorMessage) {
            }
        });
        showViaje = (TextView) findViewById(R.id.showViaje);
        IDViaje = (EditText) findViewById(R.id.edtCodigo);
    }

    public void buscar(View view){
        if(!IDViaje.getText().toString().isEmpty()){
            datoABuscar = Integer.parseInt(IDViaje.getText().toString());

            for (int i = 0; i < viajes.size(); i++){
                if(viajes.get(i).getId() == datoABuscar){
                    auxViaje = viajes.get(i);
                    IDaux = viajes.get(i).getId();
                    showViaje.setText(viajes.get(i).toString());
                    index = i;
                    found = true;
                    return;
                }
            }
            Toast.makeText(this, "No se encontró", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Ingresa un código", Toast.LENGTH_SHORT).show();
        }
    }


    public void cancelarViaje(View view) {
        if(found){
            API.DELETE_MyTrip(IDaux, usuario.getIDUser(), this, new RegisterSeatCallback() {
                @Override
                public void onAnswerCompleted(boolean completado) {
                }

                @Override
                public void onAnswerError(String errorMessage) {
                }
            });

            String[] asientos = auxViaje.getAsientos().split(" ");
            int idAsientos[] = new int[asientos.length];

            for (int i = 0; i < asientos.length; i++) {
                idAsientos[i] = Integer.parseInt(asientos[i]);

                API.DELETE_Seat_Trip(auxViaje.getIdTripAux(), idAsientos[i], this, new RegisterSeatCallback() {
                    @Override
                    public void onAnswerCompleted(boolean completado) {

                    }

                    @Override
                    public void onAnswerError(String errorMessage) {

                    }
                });
            }


            Intent intent = new Intent(this, MisViajes.class);
            intent.putExtra("usuario", usuario);
            found = false;
            index = 0;
            auxViaje = null;
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Selecciona un ID que exista >:(", Toast.LENGTH_SHORT).show();
        }
    }
}