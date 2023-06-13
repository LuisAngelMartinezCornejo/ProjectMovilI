package com.example.proyectomovil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import android.widget.ViewSwitcher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.Timer;
import java.util.TimerTask;


public class MenuPrincipal extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        DrawerLayout.DrawerListener {
    private DrawerLayout drawerLayout;
    private ImageSwitcher imageSwitcher;

    private int[] gallery = { R.drawable.a, R.drawable.b, R.drawable.d, R.drawable.e};

    private int position = 0;

    private static final Integer DURATION = 5000;
    private TextView tx;
    private Timer timer = null;

    private Usuario usuario;
    protected VideoView videoView;
    protected MediaController mediaController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        videoView = findViewById(R.id.video_view);
        mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/"
                + R.raw.video);
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.start();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setVolume(0,0);
            }
        });
        //Toast.makeText(this, "Comienza video", Toast.LENGTH_LONG).show();
        videoView.setMediaController(mediaController);

        usuario = (Usuario) getIntent().getSerializableExtra("usuario");

        tx = findViewById(R.id.welcome_message);
        tx.setText("Hola " + usuario.getNombre());
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) MenuPrincipal.this);

        MenuItem menuItem = navigationView.getMenu().getItem(0);
        drawerLayout.addDrawerListener((DrawerLayout.DrawerListener) MenuPrincipal.this);

        View header = navigationView.getHeaderView(0);
        header.findViewById(R.id.header_title).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MenuPrincipal.this, getString(R.string.title_click),
                        Toast.LENGTH_SHORT).show();
            }
        });
        imageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher);
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {

            public View makeView() {
                return new ImageView(MenuPrincipal.this);
            }
        });
        // Set animations
        // https://danielme.com/2013/08/18/diseno-android-transiciones-entre-activities/
        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        Animation fadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out);
        imageSwitcher.setInAnimation(fadeIn);
        imageSwitcher.setOutAnimation(fadeOut);
        start();

    }

    private void start() {
        if (timer != null) {
            timer.cancel();
        }
        position = 0;
        startSlider();
    }



    public void startSlider() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {

            public void run() {
                // avoid exception:
                // "Only the original thread that created a view hierarchy can touch its views"
                runOnUiThread(new Runnable() {
                    public void run() {
                        imageSwitcher.setImageResource(gallery[position]);
                        position++;
                        if (position == gallery.length) {
                            position = 0;
                        }
                    }
                });
            }
        }, 0, DURATION);
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (timer != null) {
            timer.cancel();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (timer != null) {
            startSlider();
        }

    }
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        int title = 0;
        Intent intent;
        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                Intent i = new Intent(this,MenuPrincipal.class);
                i.putExtra("usuario", usuario);
                startActivity(i);

                return true;
            case R.id.nav_crear_viaje:
                Intent inv = new Intent(this,NuevoViaje.class);
                inv.putExtra("usuario", usuario);
                //Comun.user = usuario;
                startActivity(inv);
                return true;
            case R.id.nav_mis_viaje:
                Intent imv = new Intent(this,MisViajes.class);
                imv.putExtra("usuario", usuario);
                startActivity(imv);
                return true;
            case R.id.nav_perfil:
                Intent ip = new Intent(this,MiPerfil.class);
                ip.putExtra("usuario", usuario);
                startActivity(ip);
                return true;
            case R.id.nav_salir:
                CerrarSesion();
                return true;
            default:
                throw new IllegalArgumentException("menu option not implemented!!");
        }
    }

    private void CerrarSesion()
    {
        SharedPreferences preferences = this.getSharedPreferences("user.dat", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

    }

    @Override
    public void onDrawerOpened(@NonNull View drawerView) {

    }

    @Override
    public void onDrawerClosed(@NonNull View drawerView) {

    }

    @Override
    public void onDrawerStateChanged(int newState) {

    }
}