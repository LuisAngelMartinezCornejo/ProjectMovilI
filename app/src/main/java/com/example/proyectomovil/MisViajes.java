package com.example.proyectomovil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class
MisViajes extends AppCompatActivity {

    ArrayList<Viaje> viajes = new ArrayList<>();
    RecyclerView rvMisViajes;
    TextView txtDestino, txtIDViaje, txtFecha;
    AdaptadorViaje aMv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_viajes);

        rvMisViajes = (RecyclerView) findViewById(R.id.rvMisViajes);

        viajes = Comun.MisViajes;

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rvMisViajes.setLayoutManager(llm);
        aMv = new AdaptadorViaje();
        rvMisViajes.setAdapter(aMv);
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
            return Comun.MisViajes.size();
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
                    txtDestino.setText(viajes.get(position).getLugar().getCiudad());
                    txtIDViaje.setText(String.valueOf(viajes.get(position).getId()));
                    DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
                    txtFecha.setText(String.valueOf(formatoFecha.format(viajes.get(position).getFecha())));
                } catch (Exception e) {
                    Log.e("Error eric", e.toString());
                }

            }
            Viaje v;
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MisViajes.this,MapaActivity.class);
                v = viajes.get(getLayoutPosition());
                //Toast.makeText(NuevoViaje.this,"dsfa",Toast.LENGTH_LONG).show();

                Comun.user.setViajesUsuario(v);
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

