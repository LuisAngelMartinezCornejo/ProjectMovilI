package com.example.proyectomovil;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.proyectomovil.Controller.DBCONSTS;
import com.example.proyectomovil.interfaces.AuthCallback;
import com.example.proyectomovil.interfaces.VolleyCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class API {

    private static void GET_User_Auth_private(String phone, String password, VolleyCallback callback, Context context)
    {
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
                        Toast.makeText(context, result.getString("name"), Toast.LENGTH_SHORT).show();
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

}
