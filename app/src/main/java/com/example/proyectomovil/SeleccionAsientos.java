package com.example.proyectomovil;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectomovil.interfaces.RegisterSeatCallback;
import com.example.proyectomovil.interfaces.SeatsCallback;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Random;


public class SeleccionAsientos extends AppCompatActivity {

    Viaje viaje;
    Intent intent;
    private Usuario usuario;
    Button avanzar;
    String asientos="";
    Button btnAsiento1;
    Button btnAsiento2;
    Button btnAsiento3;
    Button btnAsiento4;
    Button btnAsiento5;
    Button btnAsiento6;
    Button btnAsiento7;
    Button btnAsiento8;
    Button btnAsiento9;
    Button btnAsiento10;
    Button btnAsiento11;
    Button btnAsiento12;
    Button btnAsiento13;
    Button btnAsiento14;
    Button btnAsiento15;
    Button btnAsiento16;
    Button btnAsiento17;
    Button btnAsiento18;
    Button btnAsiento19;
    Button btnAsiento20;
    int[] selected = new int[20];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion_asientos);

        intent = getIntent();
        usuario = (Usuario) getIntent().getSerializableExtra("usuario");
        viaje = (Viaje) intent.getSerializableExtra("viaje");

        for(int i = 0; i < selected.length; i++){
            selected[i] = 0;
        }

        avanzar = findViewById(R.id.btnAvanzar);
        btnAsiento1 = findViewById(R.id.btnAsiento1);
        btnAsiento2 = findViewById(R.id.btnAsiento2);
        btnAsiento3 = findViewById(R.id.btnAsiento3);
        btnAsiento4 = findViewById(R.id.btnAsiento4);
        btnAsiento5 = findViewById(R.id.btnAsiento5);
        btnAsiento6 = findViewById(R.id.btnAsiento6);
        btnAsiento7 = findViewById(R.id.btnAsiento7);
        btnAsiento8 = findViewById(R.id.btnAsiento8);
        btnAsiento9 = findViewById(R.id.btnAsiento9);
        btnAsiento10 = findViewById(R.id.btnAsiento10);
        btnAsiento11 = findViewById(R.id.btnAsiento11);
        btnAsiento12 = findViewById(R.id.btnAsiento12);
        btnAsiento13 = findViewById(R.id.btnAsiento13);
        btnAsiento14 = findViewById(R.id.btnAsiento14);
        btnAsiento15 = findViewById(R.id.btnAsiento15);
        btnAsiento16 = findViewById(R.id.btnAsiento16);
        btnAsiento17 = findViewById(R.id.btnAsiento17);
        btnAsiento18 = findViewById(R.id.btnAsiento18);
        btnAsiento19 = findViewById(R.id.btnAsiento19);
        btnAsiento20 = findViewById(R.id.btnAsiento20);

        btnAsiento1.setOnClickListener(view->func(btnAsiento1,0));
        btnAsiento2.setOnClickListener(view->func(btnAsiento2,1));
        btnAsiento3.setOnClickListener(view->func(btnAsiento3,2));
        btnAsiento4.setOnClickListener(view->func(btnAsiento4,3));
        btnAsiento5.setOnClickListener(view->func(btnAsiento5,4));
        btnAsiento6.setOnClickListener(view->func(btnAsiento6,5));
        btnAsiento7.setOnClickListener(view->func(btnAsiento7,6));
        btnAsiento8.setOnClickListener(view->func(btnAsiento8,7));
        btnAsiento9.setOnClickListener(view->func(btnAsiento9,8));
        btnAsiento10.setOnClickListener(view->func(btnAsiento10,9));
        btnAsiento11.setOnClickListener(view->func(btnAsiento11,10));
        btnAsiento12.setOnClickListener(view->func(btnAsiento12,11));
        btnAsiento13.setOnClickListener(view->func(btnAsiento13,12));
        btnAsiento14.setOnClickListener(view->func(btnAsiento14,13));
        btnAsiento15.setOnClickListener(view->func(btnAsiento15,14));
        btnAsiento16.setOnClickListener(view->func(btnAsiento16,15));
        btnAsiento17.setOnClickListener(view->func(btnAsiento17,16));
        btnAsiento18.setOnClickListener(view->func(btnAsiento18,17));
        btnAsiento19.setOnClickListener(view->func(btnAsiento19,18));
        btnAsiento20.setOnClickListener(view->func(btnAsiento20,19));

        Hashtable<Integer, Button> asientosHashtable = new Hashtable<>();

        asientosHashtable.put(1, btnAsiento1);
        asientosHashtable.put(2, btnAsiento2);
        asientosHashtable.put(3, btnAsiento3);
        asientosHashtable.put(4, btnAsiento4);
        asientosHashtable.put(5, btnAsiento5);
        asientosHashtable.put(6, btnAsiento6);
        asientosHashtable.put(7, btnAsiento7);
        asientosHashtable.put(8, btnAsiento8);
        asientosHashtable.put(9, btnAsiento9);
        asientosHashtable.put(10, btnAsiento10);
        asientosHashtable.put(11, btnAsiento11);
        asientosHashtable.put(12, btnAsiento12);
        asientosHashtable.put(13, btnAsiento13);
        asientosHashtable.put(14, btnAsiento14);
        asientosHashtable.put(15, btnAsiento15);
        asientosHashtable.put(16, btnAsiento16);
        asientosHashtable.put(17, btnAsiento17);
        asientosHashtable.put(18, btnAsiento18);
        asientosHashtable.put(19, btnAsiento19);
        asientosHashtable.put(20, btnAsiento20);

        API.GET_Seats(viaje.getId(), this, new SeatsCallback() {
            @Override
            public void onAnswerCompleted(int[] asientosOcupados) {
                for (int i = 0; i < asientosOcupados.length; i++) {
                    Button btn = asientosHashtable.get(asientosOcupados[i]);
                    btn.setBackground(getDrawable(R.drawable.btnseleccion));
                    btn.setEnabled(false);
                }
            }

            @Override
            public void onAnswerError(String errorMessage) {

            }
        });

        avanzar.setOnClickListener(view -> {
            Intent p = new Intent(this, PrecioActivity.class);
            viaje.setAsientos(asientos);

            String[] numbersArrayString = asientos.split(" ");
            int[] numbersArray = new int[numbersArrayString.length];

            for (int i = 0; i < numbersArray.length; i++) {
                numbersArray[i] = Integer.parseInt(numbersArrayString[i]);
            }

            for (int i = 0; i < numbersArray.length; i++) {
                API.POST_Register_Seat(viaje.getId(), numbersArray[i], this, new RegisterSeatCallback() {
                    @Override
                    public void onAnswerCompleted(boolean completado) {
                    }

                    @Override
                    public void onAnswerError(String errorMessage) {
                    }
                });
            }

            p.putExtra("viaje", viaje);
            p.putExtra("usuario", usuario);
            startActivity(p);
            finish();
        });
    }

    private void funcs(int num) {
        switch (num){
            case 1:
                btnAsiento1.setEnabled(false);
                btnAsiento1.setBackgroundColor(getResources().getColor(R.color.darkgray));
                break;
            case 2:
                btnAsiento2.setEnabled(false);
                btnAsiento2.setBackgroundColor(getResources().getColor(R.color.darkgray));
                break;
            case 3:
                btnAsiento3.setEnabled(false);
                btnAsiento3.setBackgroundColor(getResources().getColor(R.color.darkgray));
                break;
            case 4:
                btnAsiento4.setEnabled(false);
                btnAsiento4.setBackgroundColor(getResources().getColor(R.color.darkgray));
                break;
            case 5:
                btnAsiento5.setEnabled(false);
                btnAsiento5.setBackgroundColor(getResources().getColor(R.color.darkgray));
                break;
            case 6:
                btnAsiento6.setEnabled(false);
                btnAsiento6.setBackgroundColor(getResources().getColor(R.color.darkgray));
                break;
            case 7:
                btnAsiento7.setEnabled(false);
                btnAsiento7.setBackgroundColor(getResources().getColor(R.color.darkgray));
                break;
            case 8:
                btnAsiento8.setEnabled(false);
                btnAsiento8.setBackgroundColor(getResources().getColor(R.color.darkgray));
                break;
            case 9:
                btnAsiento9.setEnabled(false);
                btnAsiento9.setBackgroundColor(getResources().getColor(R.color.darkgray));
                break;
            case 10:
                btnAsiento10.setEnabled(false);
                btnAsiento10.setBackgroundColor(getResources().getColor(R.color.darkgray));
                break;
            case 11:
                btnAsiento11.setEnabled(false);
                btnAsiento11.setBackgroundColor(getResources().getColor(R.color.darkgray));
                break;
            case 12:
                btnAsiento12.setEnabled(false);
                btnAsiento12.setBackgroundColor(getResources().getColor(R.color.darkgray));
                break;
            case 13:
                btnAsiento13.setEnabled(false);
                btnAsiento13.setBackgroundColor(getResources().getColor(R.color.darkgray));
                break;
            case 14:
                btnAsiento14.setEnabled(false);
                btnAsiento14.setBackgroundColor(getResources().getColor(R.color.darkgray));
                break;
            case 15:
                btnAsiento15.setEnabled(false);
                btnAsiento15.setBackgroundColor(getResources().getColor(R.color.darkgray));
                break;
            case 16:
                btnAsiento16.setEnabled(false);
                btnAsiento16.setBackgroundColor(getResources().getColor(R.color.darkgray));
                break;
            case 17:
                btnAsiento17.setEnabled(false);
                btnAsiento17.setBackgroundColor(getResources().getColor(R.color.darkgray));
                break;
            case 18:
                btnAsiento18.setEnabled(false);
                btnAsiento18.setBackgroundColor(getResources().getColor(R.color.darkgray));
                break;
            case 19:
                btnAsiento19.setEnabled(false);
                btnAsiento19.setBackgroundColor(getResources().getColor(R.color.darkgray));
                break;
            case 20:
                btnAsiento20.setEnabled(false);
                btnAsiento20.setBackgroundColor(getResources().getColor(R.color.darkgray));
                break;

        }
    }

    private int random() {
        Random r = new Random();
        int i = r.nextInt(19);
        return i;
    }

    private void func(Button btnAsiento,int pos) {
        if(selected[pos] == 0){
            selected[pos] = 1;
            if (asientos.equals("")) {
                asientos = String.valueOf(pos + 1);
            } else {
                asientos = asientos + " " + String.valueOf(pos+1);
            }
            btnAsiento.setBackgroundColor(getResources().getColor(R.color.blue2));
        }else if (selected[pos] == 1){
            selected[pos] = 0;
            btnAsiento.setBackgroundColor(getResources().getColor(R.color.gray_light));
        }
    }
}