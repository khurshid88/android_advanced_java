package com.example.android_advanced_java.activity.network.volley;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.android_advanced_java.activity.MyApplication;
import com.example.android_advanced_java.activity.model.Poster;
import com.example.android_advanced_java.activity.utils.Logger;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class VolleyHttp {
    public static String TAG = VolleyHttp.class.getSimpleName();
    public static boolean IS_TESTER = true;
    private static String SERVER_DEVELOPMENT = "https://jsonplaceholder.typicode.com/";
    private static String SERVER_PRODUCTION = "https://jsonplaceholder.typicode.com/";

    static String server(String api) {
        if (IS_TESTER)
            return SERVER_DEVELOPMENT + api;
        return SERVER_PRODUCTION + api;
    }

    static HashMap<String, String> headers() {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-type", "application/json; charset=UTF-8");
        return headers;
    }

    public static void get(String api, HashMap<String, String> params, VolleyHandler handler) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, server(api),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Logger.d(TAG, response);
                        handler.onSuccess(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Logger.e(TAG, error.toString());
                handler.onError(error.toString());
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };
        MyApplication.instance.addToRequestQueue(stringRequest);
    }

    public static void post(String api, HashMap<String, String> body, VolleyHandler handler) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, server(api),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Logger.d(TAG, response);
                        handler.onSuccess(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Logger.e(TAG, error.toString());
                handler.onError(error.toString());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return headers();
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                return new JSONObject(body).toString().getBytes();
            }
        };
        MyApplication.instance.addToRequestQueue(stringRequest);
    }

    public static void put(String api, HashMap<String, String> body, VolleyHandler handler) {
        StringRequest stringRequest = new StringRequest(Request.Method.PUT, server(api),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Logger.d(TAG, response);
                        handler.onSuccess(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Logger.e(TAG, error.toString());
                handler.onError(error.toString());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return headers();
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                return new JSONObject(body).toString().getBytes();
            }
        };
        MyApplication.instance.addToRequestQueue(stringRequest);
    }

    public static void del(String api, VolleyHandler handler) {
        StringRequest stringRequest = new StringRequest(Request.Method.DELETE, server(api),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Logger.d(TAG, response);
                        handler.onSuccess(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Logger.e(TAG, error.toString());
                handler.onError(error.toString());
            }
        });
        MyApplication.instance.addToRequestQueue(stringRequest);
    }

    /**
     * Request Api`s
     */
    public static String API_LIST_POST = "posts";
    public static String API_SINGLE_POST = "posts/"; //id
    public static String API_CREATE_POST = "posts";
    public static String API_UPDATE_POST = "posts/"; //id
    public static String API_DELETE_POST = "posts/"; //id

    /**
     * Request Param`s
     */

    public static HashMap<String, String> paramsEmpty() {
        HashMap<String, String> params = new HashMap<String, String>();
        return params;
    }

    public static HashMap<String, String> paramsCreate(Poster poster) {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("userId", String.valueOf(poster.getUserId()));
        params.put("title", poster.getTitle());
        params.put("body", poster.getBody());
        return params;
    }

    public static HashMap<String, String> paramsUpdate(Poster poster) {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("id", String.valueOf(poster.getId()));
        params.put("userId", String.valueOf(poster.getUserId()));
        params.put("title", poster.getTitle());
        params.put("body", poster.getBody());
        return params;
    }
}
