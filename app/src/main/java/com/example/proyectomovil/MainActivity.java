package com.example.proyectomovil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    String REGISTRADO = "registrado";
    String TELEFONO = "telefono";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TimerTask tarea = new TimerTask() {
            @Override
            public void run() {
                Intent i;
                if(estaRegistrado()){
                    APIArchivo API = new APIArchivo(getApplicationContext());
                    Usuario usuario = API.GET_Usuario_Telefono(getTelefonoPreferences(), getApplicationContext());
                    if (usuario.getNombre().equals("NOTFOUND"))
                    {
                        Toast.makeText(MainActivity.this, "Favor de iniciar sesión nuevamente", Toast.LENGTH_SHORT).show();
                        i = new Intent(MainActivity.this, ActivityLogin.class);
                    }
                    else
                    {
                        i = new Intent(MainActivity.this, MenuPrincipal.class);
                        i.putExtra("usuario", usuario);
                    }
                }else{
                    i = new Intent(MainActivity.this, ActivityLogin.class);
                }
                startActivity(i);
                finish();
            }
        };
        Timer tiempo = new Timer();
        tiempo.schedule(tarea,1000);
    }

    private boolean estaRegistrado() {
        SharedPreferences preferences = this.getSharedPreferences("user.dat",MODE_PRIVATE);
        return preferences.getBoolean(REGISTRADO,false);
    }

    private String getTelefonoPreferences()
    {
        SharedPreferences preferences = this.getSharedPreferences("user.dat",MODE_PRIVATE);
        return preferences.getString(TELEFONO,"");
    }
}