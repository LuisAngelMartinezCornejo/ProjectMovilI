package com.example.proyectomovil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import java.text.MessageFormat;

public class MiPerfil extends AppCompatActivity {

    Usuario user;

    TextView saludo, nombre, correo, telefono, direccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_perfil);

        user = (Usuario) getIntent().getSerializableExtra("usuario");

        saludo = (TextView) findViewById(R.id.txtSaludoPerfil);
        nombre = (TextView) findViewById(R.id.txtNombre);
        correo = (TextView) findViewById(R.id.txtCorreo);
        telefono = (TextView) findViewById(R.id.txtTelefono);
        direccion = (TextView) findViewById(R.id.txtDireccion);

        saludo.setText(MessageFormat.format("¡Hola {0}!", user.getNombre()));
        nombre.setText(user.getNombre());
        correo.setText(user.getCorreo());
        telefono.setText(String.valueOf(user.getTelefono()));
        direccion.setText(user.getDireccion());
    }

    public void editarInformacion(View view){
        Intent intent = new Intent(this, EditarPerfil.class);
        intent.putExtra("usuario", user);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MenuPrincipal.class);
        intent.putExtra("usuario", user);
        startActivity(intent);
        finish();
    }


}