package com.example.proyectomovil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.MessageFormat;

public class EditarPerfil extends AppCompatActivity {

    Usuario user;

    TextView saludo;
    EditText edtNombre, edtCorreo, edtTelefono, edtDireccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil);

        user = (Usuario) getIntent().getSerializableExtra("user");

        saludo = (TextView) findViewById(R.id.txtSaludoPerfil2);
        edtNombre = (EditText) findViewById(R.id.edtNombre);
        edtCorreo = (EditText) findViewById(R.id.edtCorreo);
        edtTelefono = (EditText) findViewById(R.id.edtTelefono);
        edtDireccion = (EditText) findViewById(R.id.edtDireccion);

        saludo.setText(MessageFormat.format("¡Hola {0}!", user.getNombre()));
        edtNombre.setText(user.getNombre());
        edtCorreo.setText(user.getCorreo());
        edtTelefono.setText(String.valueOf(user.getTelefono()));
        edtDireccion.setText(user.getDireccion());
    }

    public void guardarInformacion(View view){
        user.setNombre(edtNombre.getText().toString());
        user.setCorreo(edtCorreo.getText().toString());
        user.setTelefono(Long.parseLong(edtTelefono.getText().toString()));
        user.setDireccion(edtDireccion.getText().toString());

        Intent intent = new Intent(this, MiPerfil.class);
        intent.putExtra("user", user);
        startActivity(intent);
        finish();
    }
}