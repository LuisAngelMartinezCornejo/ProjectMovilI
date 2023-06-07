package com.example.proyectomovil;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class API {

    public static Usuario GET_User_Auth(String phone, String password)
    {
        Usuario usuario = new Usuario();
        usuario.setNombre("");
        StringRequest request = new StringRequest(Request.Method.GET, DBCONSTS.URL_AUTH + phone,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("items");

                            JSONObject result = jsonArray.getJSONObject(0);

                            if (phone.equals(result.getString("phone").trim()))
                            {
                                if (password.equals(result.getString("password").trim()))
                                {
                                    usuario.setNombre(result.getString("nombre"));
                                    usuario.setIDUser(result.getInt("iduser"));
                                    usuario.setCorreo(result.getString("mail"));
                                    usuario.setTelefono(Long.parseLong(result.getString("phone")));
                                    usuario.setDireccion(result.getString("direction"));
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return super.getParams();
            }
        };
        return usuario;
    }
}
