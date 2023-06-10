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

import com.example.proyectomovil.interfaces.AllTripsCallback;
import com.example.proyectomovil.interfaces.AuthCallback;

import java.util.ArrayList;
import java.util.Date;

public class NuevoViaje extends AppCompatActivity  {
    private Usuario user = new Usuario();

    static ArrayList<Viaje> viajesChido = new ArrayList<>();
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

        user = (Usuario) getIntent().getSerializableExtra("user");

        rvViajes = (RecyclerView) findViewById(R.id.rvViajes);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rvViajes.setLayoutManager(llm);
        av = new AdaptadorViaje();
        rvViajes.setAdapter(av);

        API.GET_All_Trips(this,new AllTripsCallback() {

            @Override
            public void onAnswerCompleted(ArrayList<Viaje> viajes) {
                viajesChido.addAll(viajes);
                av.notifyDataSetChanged();
            }

            @Override
            public void onAnswerError(String errorMessage) {
                Toast.makeText(NuevoViaje.this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
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
            return viajesChido.size();
        }



        class AdaptadorViajeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            public AdaptadorViajeHolder(@NonNull View itemView) {
                super(itemView);
                txtDestino = itemView.findViewById(R.id.txtDestinoViaje);
                itemView.setOnClickListener(this);
            }

            public void imprimir(int position){
                txtDestino.setText(viajesChido.get(position).getLugar().getCiudad());
            }
            Viaje v;
            @Override
            public void onClick(View view) {
                Intent i = new Intent(NuevoViaje.this,PantallaViaje.class);
                v = viajesChido.get(getLayoutPosition());
                user.setViajesUsuario(v);
                i.putExtra("user", user);
                startActivity(i);
                finish();
            }
        }
    }
}