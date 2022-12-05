package com.example.proyectomovil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class NuevoViaje extends AppCompatActivity  {

    ArrayList<Viaje> viajes = new ArrayList<>();
    RecyclerView rvViajes;
    TextView txtDestino, txtDias, txtTransporte, txtCantidadBoletos;
    AdaptadorViaje av;

    Lugar vallarta = new Lugar(2, "Vallarta", "Jalisco", "México", true);

    Lugar cancun = new Lugar(1, "Cancun", "Quintana Roo", "México", true);
    Transporte avion = new Transporte("Avión", "Volaris", "F101", "20310", "Luis Cornejo");
    Date viajeCancun = new Date(2022, 12, 15);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_viaje);

        rvViajes = (RecyclerView) findViewById(R.id.rvViajes);
        viajes = new ArrayList<Viaje>();
        viajes.add(new Viaje(1, cancun, avion, viajeCancun, 7, "4", "Eric Martinez"));
        viajes.add(new Viaje(2, vallarta, avion, viajeCancun, 5, "2", "Mauricio Farfan"));

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rvViajes.setLayoutManager(llm);
        av = new AdaptadorViaje();
        rvViajes.setAdapter(av);
    }

    private class AdaptadorViaje extends RecyclerView.Adapter<AdaptadorViaje.AdaptadorViajeHolder> {

        @NonNull
        @Override
        public AdaptadorViaje.AdaptadorViajeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new AdaptadorViajeHolder(getLayoutInflater().inflate(R.layout.itemrecyclerview, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull AdaptadorViaje.AdaptadorViajeHolder holder, int position) {
            holder.imprimir(position);
        }

        @Override
        public int getItemCount() {
            return viajes.size();
        }



        class AdaptadorViajeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            public AdaptadorViajeHolder(@NonNull View itemView) {
                super(itemView);
                txtDestino = itemView.findViewById(R.id.txtDestinoViaje);
                txtDias = itemView.findViewById(R.id.txtDiasViaje);
                txtTransporte = itemView.findViewById(R.id.txtTransporteViaje);
                txtCantidadBoletos = itemView.findViewById(R.id.txtCantidadBoletos);
                itemView.setOnClickListener(this);
            }

            public void imprimir(int position){
                txtDestino.setText(viajes.get(position).getLugar().getCiudad());
                txtDias.setText(String.valueOf(viajes.get(position).getDiasEstancia()));
                txtTransporte.setText(viajes.get(position).getTransporte().getTipo());
                txtCantidadBoletos.setText(viajes.get(position).getAsientos());
            }
            Viaje v;
            @Override
            public void onClick(View view) {
                Intent i = new Intent(NuevoViaje.this,PantallaViaje.class);
                v = viajes.get(getLayoutPosition());
                //Toast.makeText(NuevoViaje.this,"dsfa",Toast.LENGTH_LONG).show();

                Comun.user.setViajesUsuario(v);
                startActivity(i);
                finish();
            }
        }
    }
}