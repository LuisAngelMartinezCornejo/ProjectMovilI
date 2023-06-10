package com.example.proyectomovil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectomovil.interfaces.RegisterUserCallback;
import com.example.proyectomovil.interfaces.UpdateUserCallback;

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

        API.PUT_Update_User(user, this, new UpdateUserCallback() {
            @Override
            public void onAnswerCompleted(boolean resultado) {
                if (resultado)
                {
                    Intent intent = new Intent(EditarPerfil.this, MiPerfil.class);
                    intent.putExtra("user", user);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onAnswerError(String errorMessage) {
                Toast.makeText(EditarPerfil.this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }
}