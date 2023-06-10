package com.example.proyectomovil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectomovil.interfaces.AuthCallback;
import com.example.proyectomovil.interfaces.UserCallback;

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
                if(estaRegistrado()){
                    String telefono = getTelefonoPreferences();
                    API.GET_User(telefono, MainActivity.this, new UserCallback() {
                        @Override
                        public void onAnswerCompleted(Usuario user) {
                            Intent intent = new Intent(MainActivity.this, MenuPrincipal.class);
                            intent.putExtra("usuario", user);
                            startActivity(intent);
                            finish();
                        }

                        @Override
                        public void onAnswerError(String errorMessage) {
                            Intent intent = new Intent(MainActivity.this, ActivityLogin.class);
                            startActivity(intent);
                            finish();
                        }
                    });
                }else{
                    Intent i = new Intent(MainActivity.this, ActivityLogin.class);
                    startActivity(i);
                    finish();
                }
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