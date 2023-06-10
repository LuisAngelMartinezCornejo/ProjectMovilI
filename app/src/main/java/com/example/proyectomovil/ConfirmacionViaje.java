package com.example.proyectomovil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectomovil.interfaces.AllTripsCallback;
import com.example.proyectomovil.interfaces.RegisterMyTripCallback;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

public class ConfirmacionViaje extends AppCompatActivity {

    TextView idViaje, destino, transporte, fecha, dias, asientos, reservacionNombre;
    Viaje viaje;
    Intent intent;
    private Usuario usuario;
    private PendingIntent pendingIntentMisViajes;
    private final static String CHANEL_ID = "NOTIFICACION";
    public final static int NOTIFICACION_ID = 0;

    String targetDateString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmacion_viaje);

        intent = getIntent();
        viaje = (Viaje) intent.getSerializableExtra("viaje");
        usuario = (Usuario) getIntent().getSerializableExtra("usuario");

        idViaje = (TextView) findViewById(R.id.txtIDViaje);
        destino = (TextView) findViewById(R.id.txtDestino);
        transporte = (TextView) findViewById(R.id.txtTransporte);
        fecha = (TextView) findViewById(R.id.txtFecha);
        dias = (TextView) findViewById(R.id.txtDias);
        asientos = (TextView) findViewById(R.id.txtAsientos);
        reservacionNombre = (TextView) findViewById(R.id.txtReservacion);

        idViaje.setText(String.valueOf(viaje.getId()));
        destino.setText(viaje.getLugar().getCiudad());
        transporte.setText(viaje.getTransporte().getTipo());
        fecha.setText(viaje.getFecha().toString());
        dias.setText(String.valueOf(viaje.getDiasEstancia()));
        asientos.setText(viaje.getAsientos());
        reservacionNombre.setText(viaje.getNombreReserva());

        String originalDateString = viaje.getFecha().toString();

        SimpleDateFormat originalFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        originalFormat.setTimeZone(TimeZone.getTimeZone("GMT"));

        SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            Date originalDate = originalFormat.parse(originalDateString);
            String targetDateString = targetFormat.format(originalDate);
            System.out.println(targetDateString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setPendingIntentInicio() {
        Intent intent = new Intent(this, MenuPrincipal.class);
        intent.putExtra("usuario", usuario);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(this);
        stackBuilder.addNextIntent(intent);
        pendingIntentMisViajes = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_IMMUTABLE);
    }

    private void crearCanalNotificacion() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "TicketGo";

            NotificationChannel notificationChannel = new NotificationChannel(CHANEL_ID, name, NotificationManager.IMPORTANCE_HIGH);
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    private void crearNotificacion() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(),CHANEL_ID);
        builder.setSmallIcon(R.drawable.baseline_notifications_active_24);
        builder.setContentText("Estado:");
        builder.setColor(Color.BLACK);
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setDefaults(Notification.DEFAULT_SOUND);
        builder.setContentIntent(pendingIntentMisViajes);
        builder.addAction(R.drawable.baseline_business_center_24, "Viaje comprado con éxito.", pendingIntentMisViajes);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
        notificationManagerCompat.notify(NOTIFICACION_ID, builder.build());
    }

    // MOSTRAR LA NOTIFICACION Y ENVIAR EL OBJETO TIPO VIAJE ENVIAR EL VIAJE A LA BD
    public void entendido(View view){
        API.POST_Register_Trip(usuario.getIDUser(), viaje.getId(), viaje.getDiasEstancia(), viaje.getAsientos(),
                viaje.getNombreReserva(), targetDateString, this,new RegisterMyTripCallback() {

                    @Override
                    public void onAnswerCompleted(boolean completado) {
                        if (completado) {
                            Toast.makeText(ConfirmacionViaje.this, "Agregado a mis viajes.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ConfirmacionViaje.this, "Ocurrió un error.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onAnswerError(String errorMessage) {
                        Toast.makeText(ConfirmacionViaje.this, errorMessage, Toast.LENGTH_SHORT).show();
                    }
                });

        setPendingIntentInicio();
        crearCanalNotificacion();
        crearNotificacion();
        finish();
    }
}