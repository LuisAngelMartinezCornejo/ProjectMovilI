package com.example.proyectomovil;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;

public class NuevoViaje extends AppCompatActivity  {

    ArrayList<Viaje> viajes = new ArrayList<>();
    RecyclerView rvViajes;
    TextView txtDestino;
    AdaptadorViaje av;

    Lugar vallarta = new Lugar(2, "Vallarta", "Jalisco", "México", true);
    Lugar cabos = new Lugar(3, "Cabos", "Baja California Sur", "México", true);
    Lugar tokio = new Lugar(4, "Tokio", "Maruchan", "Japon", true);

    Lugar cancun = new Lugar(1, "Cancun", "Quintana Roo", "México", true);
    Transporte avion = new Transporte("Avión", "Volaris", "F101", "20310", "Luis Cornejo");
    Transporte camion = new Transporte("Camión", "Volaris", "F101", "20310", "Luis Cornejo");
    Transporte apata = new Transporte("A pie", "Volaris", "F101", "20310", "Luis Cornejo");
    Date viajeCancun = new Date(122, 11, 15);
    Date viajeTokio = new Date(123, 5, 6);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_viaje);

        rvViajes = (RecyclerView) findViewById(R.id.rvViajes);
        viajes = new ArrayList<Viaje>();
        viajes.add(new Viaje(1, cancun, avion, viajeCancun, 7, "4", Comun.user.getNombre()));
        viajes.add(new Viaje(2, vallarta, camion, viajeTokio, 5, "2", Comun.user.getNombre()));
        viajes.add(new Viaje(3, cabos, apata, viajeTokio, 9, "5", Comun.user.getNombre()));
        viajes.add(new Viaje(4, tokio, avion, viajeCancun, 15, "2", Comun.user.getNombre()));

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rvViajes.setLayoutManager(llm);
        av = new AdaptadorViaje();
        rvViajes.setAdapter(av);
    }

    private class AdaptadorViaje extends RecyclerView.Adapter<AdaptadorViaje.AdaptadorViajeHolder> {

        @NonNull
        @Override
        public AdaptadorViajeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new AdaptadorViajeHolder(getLayoutInflater().inflate(R.layout.itemrecyclerview, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull AdaptadorViajeHolder holder, int position) {
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
                itemView.setOnClickListener(this);
            }

            public void imprimir(int position){
                txtDestino.setText(viajes.get(position).getLugar().getCiudad());
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