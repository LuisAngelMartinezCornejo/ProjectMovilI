package com.example.proyectomovil;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.proyectomovil.Controller.DBCONSTS;
import com.example.proyectomovil.interfaces.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class API {

    private static void GET_User_Auth_private(String phone, String password, VolleyCallback callback, Context context) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String requestString = DBCONSTS.URL_AUTH + phone;
        StringRequest request = new StringRequest(Request.Method.GET, requestString,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject JSONresponse = new JSONObject(response);
                            callback.onSuccess(JSONresponse);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            callback.onError("INVALID JSON");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {}
                });
        queue.add(request);
    }
    public static void GET_User_Auth(String phone, String password, Context context, AuthCallback callback) {
        Usuario usuario = new Usuario();
        usuario.setNombre("");

        GET_User_Auth_private(phone, password, new VolleyCallback() {
            @Override
            public void onSuccess(JSONObject response) {
                JSONArray jsonArray = null;
                try {
                    jsonArray = response.getJSONArray("items");
                    JSONObject result = jsonArray.getJSONObject(0);

                    if (phone.equals(result.getString("phone").trim()) && password.equals(result.getString("pass").trim())) {
                        usuario.setNombre(result.getString("name"));
                        usuario.setIDUser(result.getInt("iduser"));
                        usuario.setCorreo(result.getString("mail"));
                        usuario.setTelefono(Long.parseLong(result.getString("phone")));
                        usuario.setDireccion(result.getString("direction"));
                    }

                    callback.onUserAuthCompleted(usuario);
                } catch (JSONException e) {
                    e.printStackTrace();
                    callback.onAuthError(e.getMessage());
                }
            }

            @Override
            public void onError(String errorMessage) {
                callback.onAuthError(errorMessage);
            }
        }, context);
    }

    public static void GET_All_Trips(Context context, AllTripsCallback callback)
    {
        RequestQueue queue = Volley.newRequestQueue(context);
        String requestString = DBCONSTS.URL_ALL_TRIPS;
        StringRequest request = new StringRequest(Request.Method.GET, requestString,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject JSONResponse = new JSONObject(response);
                            JSONArray jsonArray = JSONResponse.getJSONArray("items");

                            ArrayList<Viaje> misViajes = new ArrayList<>();
                            for (int i = 0; i < jsonArray.length(); i++)
                            {
                                JSONObject objeto = jsonArray.getJSONObject(i);
                                misViajes.add(new Viaje(
                                    objeto.getInt("idtrip"),
                                    objeto.getString("departuredate"),
                                    objeto.getString("city"),
                                    objeto.getString("transporttype")));
                            }
                            callback.onAnswerCompleted(misViajes);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            callback.onAnswerError(e.getMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {}
                });
        queue.add(request);

    }

    public static void POST_Register_User(String nombre, String telefono, String contrasena, Context context, RegisterUserCallback callback)
    {
        RequestQueue queue = Volley.newRequestQueue(context);
        String requestString = DBCONSTS.URL_REGISTER_USER;
        JSONObject requestObject = new JSONObject();
        try {
            requestObject.put("NAME", nombre);
            requestObject.put("PHONE", telefono);
            requestObject.put("PASS", contrasena);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, requestString, requestObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {}
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {}
                })
                {
                    @Override
                    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response)
                    {
                        int statusCode = response.statusCode;
                        if (statusCode == 201)
                            callback.onAnswerCompleted(true);
                        else
                            callback.onAnswerError("Operation failed");
                        return null;
                    }
                };
        queue.add(request);
    }

    public static void PUT_Update_User(Usuario usuario, Context context, UpdateUserCallback callback)
    {
        RequestQueue queue = Volley.newRequestQueue(context);
        String requestString = DBCONSTS.URL_UPDATE_USER_INFO;
        JSONObject requestObject = new JSONObject();
        try {
            requestObject.put("NAME", usuario.getNombre());
            requestObject.put("MAIL", usuario.getCorreo());
            requestObject.put("DIRECTION", usuario.getDireccion());
            requestObject.put("PHONE", usuario.getTelefono());
            requestObject.put("IDUSER", usuario.getIDUser());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT, requestString, requestObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {}
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {}
                })
                {
                    @Override
                    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response)
                    {
                        int statusCode = response.statusCode;
                        if (statusCode == 200)
                            callback.onAnswerCompleted(true);
                        else
                            callback.onAnswerError("Operation failed");
                        return null;
                    }
                };
        queue.add(request);
    }

    public static void GET_My_Trips(int IDUser, Context context, MyTripsCallback callback)
    {
        {
            RequestQueue queue = Volley.newRequestQueue(context);
            String requestString = DBCONSTS.URL_MY_TRIPS + IDUser;
            StringRequest request = new StringRequest(Request.Method.GET, requestString,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject JSONResponse = new JSONObject(response);
                                JSONArray jsonArray = JSONResponse.getJSONArray("items");

                                ArrayList<Viaje> misViajes = new ArrayList<>();
                                for (int i = 0; i < jsonArray.length(); i++)
                                {
                                    JSONObject objeto = jsonArray.getJSONObject(i);
                                    misViajes.add(new Viaje(
                                            objeto.getInt("idmytrip"),
                                            objeto.getString("city"),
                                            objeto.getString("departuredate")));
                                }
                                callback.onAnswerCompleted(misViajes);
                            } catch (JSONException e) {
                                e.printStackTrace();
                                callback.onAnswerError(e.getMessage());
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {}
                    });
            queue.add(request);

        }
    }
}
