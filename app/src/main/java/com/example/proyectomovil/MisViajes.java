package com.example.proyectomovil;

import android.os.Bundle;
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
import java.util.Date;

public class
MisViajes extends AppCompatActivity {


    RecyclerView rvMisViajes;
    TextView txtDestino, txtViaje, txtFecha;
    AdaptadorViaje aMv;

    //Usuario user;

    Lugar cancun = new Lugar(1, "Cancun", "Quintana Roo", "México", true);
    Transporte avion = new Transporte("Avión", "Volaris", "F101", "20310", "Luis Cornejo");
    Date viajeCancun = new Date(122,05,15);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_viajes);

        //user = (Usuario) getIntent().getSerializableExtra("user");

        rvMisViajes = (RecyclerView) findViewById(R.id.rvMisViajes);

        rvMisViajes.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

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
                txtViaje = itemView.findViewById(R.id.txtIDViajeMV);
                txtFecha = itemView.findViewById(R.id.txtFechaMV);
                itemView.setOnClickListener(this);
            }


            public void imprimir(int position){
                txtDestino.setText(Comun.MisViajes.get(0).getLugar().getCiudad());
                txtViaje.setText(String.valueOf(Comun.MisViajes.get(0).getId()));
                DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
                txtFecha.setText(String.valueOf(formatoFecha.format((Comun.MisViajes.get(0).getFecha()))));
            }


            @Override
            public void onClick(View view) {
            }
        }
    }

    public void CancelarViaje(View view){

    }
}