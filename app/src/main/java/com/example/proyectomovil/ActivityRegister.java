package com.example.proyectomovil;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class ActivityRegister extends AppCompatActivity {

    private EditText editTextNombre, editTextTelefono, editTextContrasena;
    private CheckBox checkBoxTerminos;

    private String archivoUsuarios = "usuarios.txt";
    private OutputStreamWriter output;
    private InputStreamReader input;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextNombre = findViewById(R.id.edt_username);
        editTextTelefono = findViewById(R.id.edt_number_register);
        editTextContrasena = findViewById(R.id.edt_password_regsiter);
        checkBoxTerminos = findViewById(R.id.checkbox__register);
    }

    public void btnIniciar(View view)
    {
        String nombre = editTextNombre.getText().toString();
        String telefono = editTextTelefono.getText().toString();
        String contrasena = editTextContrasena.getText().toString();

        if (nombre.equals("") || telefono.equals("") || contrasena.equals(""))
            Toast.makeText(this, "Por favor, ingrese todos sus datos.", Toast.LENGTH_LONG).show();
        else if (!checkBoxTerminos.isChecked())
            Toast.makeText(this, "Por favor, acepte los TyC para continuar.", Toast.LENGTH_LONG).show();
        else
        {
            Usuario usuario = new Usuario();
            usuario.setNombre(nombre);
            usuario.setTelefono(Integer.parseInt(telefono));
            usuario.setContraseña(contrasena);

            APIArchivo API = new APIArchivo(this);
            usuario = API.POST_Usuario(usuario, this);

            Intent intent = new Intent(ActivityRegister.this, MenuPrincipal.class);
            intent.putExtra("usuario", usuario);
            startActivity(intent);
            finish();
        }
    }

    public void btnInicioSesion(View view)
    {
        Intent intent = new Intent(ActivityRegister.this, ActivityLogin.class);
        startActivity(intent);
        finish();
    }



}