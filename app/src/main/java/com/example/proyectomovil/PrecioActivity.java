package com.example.proyectomovil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class PrecioActivity extends AppCompatActivity {

    TextView txtPrecioActual, txtPrecioDescuento;
    Viaje viaje;
    private Usuario usuario;
    Intent intent;
    float descuento = 0;
    float precio = 0;
    float precioDescuento = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_precio);

        intent = getIntent();
        viaje = (Viaje) intent.getSerializableExtra("viaje");
        usuario = (Usuario) getIntent().getSerializableExtra("usuario");

        txtPrecioActual = findViewById(R.id.txtPrecioActual);
        txtPrecioDescuento = findViewById(R.id.txtPrecioDescuento);

        precio = (int) ((Math.random() * 5000) + 1000);
        txtPrecioActual.setText(String.valueOf(precio));
    }

    public void escanear (View view) {
        IntentIntegrator intentIntegrator = new IntentIntegrator (PrecioActivity.this);
        intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        intentIntegrator.setPrompt ("Lector - CDP");
        intentIntegrator.setCameraId (0);
        intentIntegrator.setBeepEnabled(true);
        intentIntegrator.setBarcodeImageEnabled(true);
        intentIntegrator.initiateScan() ;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);

        if(intentResult != null ){
            if(intentResult.getContents()== null){
                Toast.makeText (this, "Escaneo cancelado.", Toast.LENGTH_SHORT) .show();
            } else {
                Toast.makeText(this, "Datos escaneados. ", Toast.LENGTH_SHORT).show();
                descuento = Integer.parseInt(intentResult.getContents());

                precioDescuento = precio - ((precio * descuento) / 100);
                txtPrecioDescuento.setText(String.valueOf(precioDescuento));
            }
        } else {
            super.onActivityResult (requestCode, resultCode, data);
        }
    }

    public void confirmarCompra (View view) {
        Intent confirmar = new Intent(this, ConfirmacionViaje.class);
        confirmar.putExtra("viaje", viaje);
        confirmar.putExtra("usuario", usuario);
        startActivity(confirmar);
        finish();
    }
}