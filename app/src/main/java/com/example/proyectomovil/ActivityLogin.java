package com.example.proyectomovil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


public class ActivityLogin extends AppCompatActivity {

    EditText editTextTelefono, editTextContrasena;
    CheckBox checkPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextTelefono = findViewById(R.id.edt_number_login);
        editTextContrasena = findViewById(R.id.edt_password);
        checkPreferences = findViewById(R.id.checkbox_login);
    }

    public void btnIniciar(View view)
    {
        String telefono = editTextTelefono.getText().toString();
        String contrasena = editTextContrasena.getText().toString();
        if (telefono.equals("") || contrasena.equals(""))
            Toast.makeText(this, "Por favor, ingrese todos sus datos.", Toast.LENGTH_LONG).show();
        else
        {

            Usuario usuario = API.GET_User_Auth(telefono, contrasena);
            if (usuario.getNombre().equals(""))
                Toast.makeText(this, "Contraseña incorrecta. Favor de verificar", Toast.LENGTH_SHORT).show();
            else
            {
                if (checkPreferences.isChecked())
                    SetPreferences(usuario);
                Intent intent = new Intent(ActivityLogin.this, MenuPrincipal.class);
                intent.putExtra("usuario", usuario);
                startActivity(intent);
                finish();
            }


            /*APIArchivo API = new APIArchivo(this);
            Usuario usuario = API.GET_Usuario(telefono, contrasena, this);

            if (usuario.getNombre().equals("ERROR"))
                Toast.makeText(this, "Contraseña incorrecta. Favor de verificar", Toast.LENGTH_SHORT).show();
            else if (usuario.getNombre().equals("NOTFOUND"))
                Toast.makeText(this, "Usuario inexistente. Favor de verificar", Toast.LENGTH_SHORT).show();
            else
            {
                if (checkPreferences.isChecked())
                    SetPreferences(usuario);
                Intent intent = new Intent(ActivityLogin.this, MenuPrincipal.class);
                intent.putExtra("usuario", usuario);
                startActivity(intent);
                finish();
            }*/
        }
    }

    private void SetPreferences(Usuario usuario)
    {
        SharedPreferences preferences = this.getSharedPreferences("user.dat", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("registrado", true);
        editor.putString("telefono", Long.toString(usuario.getTelefono()));
        editor.apply();
    }

    public void btnRegistrarse(View view)
    {
        Intent intent = new Intent(ActivityLogin.this, ActivityRegister.class);
        startActivity(intent);
    }
}