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

    public static boolean GET_User_Auth(String phone, String password)
    {
        final int[] myResult = {0};
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
                                    myResult[0] = 1;
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
        return myResult[0] == 1;
    }
}
