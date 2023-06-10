package com.example.proyectomovil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.maps.android.PolyUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;


public class MapaActivity extends AppCompatActivity implements OnMapReadyCallback {
    private Geocoder geocoder;
    private PlacesClient placesClient;
    private GoogleMap mMap;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    Location currentLocation;
    private FusedLocationProviderClient fusedLocationClient;
    Address address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        getLastLocation();

        geocoder = new Geocoder(this, Locale.getDefault());
        String addressString = "Puerta de Hierro, Jal.";

        String res = getApi();
        convertAddressToLocation(addressString);

    }

    private String getApi() {
        Properties properties = new Properties();
        try {
            properties.load(getAssets().open("local.properties"));
            String apiKey = properties.getProperty("API_ROUTE_GOOGLE");
            Places.initialize(getApplicationContext(), apiKey);
            placesClient = Places.createClient(this);
            return apiKey;
        } catch (IOException e) {
            e.printStackTrace();
            return "no";
        }

    }

    private void convertAddressToLocation(String addressString) {
        try {
            List<Address> addressList = geocoder.getFromLocationName(addressString, 1);
            if (addressList != null && !addressList.isEmpty()) {
                address = addressList.get(0);
                double latitude = address.getLatitude();
                double longitude = address.getLongitude();
                LatLng latLng = new LatLng(latitude, longitude);

                // Crear una ubicación Location
                Location location = new Location("");
                location.setLatitude(latitude);
                location.setLongitude(longitude);

                // Utilizar la ubicación
                //Toast.makeText(MapaActivity.this, "Latitud: " + latitude + ", Longitud: " + longitude, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MapaActivity.this, "Dirección no encontrada", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    private void getLastLocation() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},LOCATION_PERMISSION_REQUEST_CODE);
            return;
        }
        Task<Location> task = fusedLocationClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location!=null){
                    currentLocation = location;
                    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
                    mapFragment.getMapAsync(MapaActivity.this);

                }
            }
        });
    }

    private void addMarker(Address a, String title) {
        LatLng l = new LatLng(a.getLatitude(),a.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions()
                .position(l)
                .title(title);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(l));
        mMap.addMarker(markerOptions);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
            mMap = googleMap;
            mMap.setMinZoomPreference(4f);
            mMap.setMaxZoomPreference(20f);
            // Add a marker in Sydney and move the camera
            LatLng origin = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
            mMap.addMarker(new MarkerOptions()
                    .position(origin)
                    .title("Marker in location"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(origin));
            addMarker(address,"Destino");
            LatLng destino = new LatLng(address.getLatitude(),address.getLongitude());
        try {
            traceRoute(origin,destino);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void traceRoute(LatLng fromLatLng, LatLng toLatLng) throws IOException {
        List<List<LatLng>> path = new ArrayList<>();

        String urlDirections = "https://maps.googleapis.com/maps/api/directions/json?origin=" + fromLatLng.latitude + "," + fromLatLng.longitude + "&destination=" + toLatLng.latitude + "," + toLatLng.longitude + "&key=" + getString(R.string.api);

        StringRequest directionsRequest = new StringRequest(Request.Method.GET, urlDirections,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            // Get routes
                            JSONArray routes = jsonResponse.getJSONArray("routes");
                            JSONObject legs = routes.getJSONObject(0).getJSONArray("legs").getJSONObject(0);
                            JSONArray steps = legs.getJSONArray("steps");
                            for (int i = 0; i < steps.length(); i++) {
                                String points = steps.getJSONObject(i).getJSONObject("polyline").getString("points");
                                path.add(PolyUtil.decode(points));
                            }
                            for (int i = 0; i < path.size(); i++) {
                                mMap.addPolyline(new PolylineOptions().addAll(path.get(i)).color(Color.BLUE));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Manejar el error de la solicitud
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(directionsRequest);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == LOCATION_PERMISSION_REQUEST_CODE){
            if(grantResults.length > 0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                getLastLocation();
            }else{
                Toast.makeText(this,"Permisos no dados",Toast.LENGTH_SHORT).show();
            }
        }
    }
}