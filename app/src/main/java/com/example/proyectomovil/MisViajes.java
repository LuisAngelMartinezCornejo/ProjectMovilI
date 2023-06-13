package com.example.proyectomovil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectomovil.interfaces.MyTripsCallback;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class
MisViajes extends AppCompatActivity {

    private Usuario usuario;
    private Intent intent;
    ArrayList<Viaje> viajes = new ArrayList<>();
    RecyclerView rvMisViajes;
    TextView txtDestino, txtIDViaje, txtFecha;
    AdaptadorViaje aMv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_viajes);

        intent = getIntent();
        usuario = (Usuario) getIntent().getSerializableExtra("usuario");

        rvMisViajes = (RecyclerView) findViewById(R.id.rvMisViajes);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rvMisViajes.setLayoutManager(llm);
        aMv = new AdaptadorViaje();
        rvMisViajes.setAdapter(aMv);
        API.GET_My_Trips(usuario.getIDUser(), this, new MyTripsCallback() {
            @Override
            public void onAnswerCompleted(ArrayList<Viaje> misViajes) {
                viajes.addAll(misViajes);
                aMv.notifyDataSetChanged();
            }

            @Override
            public void onAnswerError(String errorMessage) {
                Toast.makeText(MisViajes.this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private class AdaptadorViaje extends RecyclerView.Adapter<AdaptadorViaje.AdaptadorViajeHolder> {

        @NonNull
        @Override
        public AdaptadorViajeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new AdaptadorViajeHolder(getLayoutInflater().inflate(R.layout.recyclerview_mis_viajes, parent, false));
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
                txtDestino = itemView.findViewById(R.id.txtDestinoMV);
                txtIDViaje = itemView.findViewById(R.id.txtIDViajeMV);
                txtFecha = itemView.findViewById(R.id.txtFechaMV);
                itemView.setOnClickListener(this);
            }

            public void imprimir(int position){
                try {
                    txtIDViaje.setText(String.valueOf(viajes.get(position).getId()));
                    txtDestino.setText(viajes.get(position).getLugar().getCiudad());
                    DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
                    txtFecha.setText(String.valueOf(formatoFecha.format(viajes.get(position).getFecha())));
                } catch (Exception e) {
                    Log.e("Error Eric", e.toString());
                }

            }
            Viaje v;
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MisViajes.this, MapaActivity.class);
                v = viajes.get(getLayoutPosition());
                usuario.setViajesUsuario(v);
                i.putExtra("usuario", usuario);
                startActivity(i);
                finish();
            }
        }
    }

    public void CancelarViaje(View view){
        Intent intent = new Intent(this, EditarViajes.class);
        startActivity(intent);
        finish();
    }
}

