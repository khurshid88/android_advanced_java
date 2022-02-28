package com.example.android_advanced_java.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_advanced_java.R;
import com.example.android_advanced_java.activity.adapter.PosterAdapter;
import com.example.android_advanced_java.activity.model.Poster;
import com.example.android_advanced_java.activity.model.PosterResp;
import com.example.android_advanced_java.activity.network.retrofit.RetrofitHttp;
import com.example.android_advanced_java.activity.network.volley.VolleyHandler;
import com.example.android_advanced_java.activity.network.volley.VolleyHttp;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//https://www.geeksforgeeks.org/auto-hide-or-auto-extend-floating-action-button-in-recyclerview-in-android/
public class NetworkActivity extends BaseActivity {
    RecyclerView recyclerView;
    ArrayList<Poster> posters = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);
        initViews();
    }

    void initViews() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        FloatingActionButton b_floating = findViewById(R.id.b_floating);

        apiPosterList();
    }

    void refreshAdapter(ArrayList<Poster> posters) {
        PosterAdapter adapter = new PosterAdapter(this, posters);
        recyclerView.setAdapter(adapter);
    }

    public void dialogPoster(Poster poster) {
        new AlertDialog.Builder(this)
                .setTitle("Delete Poster")
                .setMessage("Are you sure you want to delete this poster?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        apiPosterDelete(poster);
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void apiPosterList() {
        VolleyHttp.get(VolleyHttp.API_LIST_POST, VolleyHttp.paramsEmpty(), new VolleyHandler() {
            @Override
            public void onSuccess(String response) {
                Poster[] postArray = new Gson().fromJson(response, Poster[].class);
                for (Poster poster : postArray) {
                    posters.add(poster);
                }
                refreshAdapter(posters);
                Log.d("@@@onResponse ", "" + posters.size());
            }

            @Override
            public void onError(String error) {
                Log.d("@@@onErrorResponse ", error);
            }
        });
    }

    private void apiPosterDelete(Poster poster) {
        VolleyHttp.del(VolleyHttp.API_DELETE_POST + poster.getId(), new VolleyHandler() {
            @Override
            public void onSuccess(String response) {
                Log.d("@@@onResponse ", response);
                apiPosterList();
            }

            @Override
            public void onError(String error) {
                Log.d("@@@onErrorResponse ", error);
            }
        });
    }

    void workWithVolley() {

        VolleyHttp.get(VolleyHttp.API_LIST_POST, VolleyHttp.paramsEmpty(), new VolleyHandler() {
            @Override
            public void onSuccess(String response) {
            }

            @Override
            public void onError(String error) {

            }
        });

//        Poster poster = new Poster(1, 1, "PDP", "Online");
//        VolleyHttp.post(VolleyHttp.API_CREATE_POST, VolleyHttp.paramsCreate(poster), new VolleyHandler() {
//            @Override
//            public void onSuccess(String response) {
//                Log.d("@@@onResponse ", response);
//            }
//
//            @Override
//            public void onError(String error) {
//                Log.d("@@@onErrorResponse ", error);
//            }
//        });
//
//        VolleyHttp.put(VolleyHttp.API_UPDATE_POST + poster.getId(), VolleyHttp.paramsUpdate(poster), new VolleyHandler() {
//            @Override
//            public void onSuccess(String response) {
//                Log.d("@@@onResponse ", response);
//            }
//
//            @Override
//            public void onError(String error) {
//                Log.d("@@@onErrorResponse ", error);
//            }
//        });
//
//        VolleyHttp.del(VolleyHttp.API_DELETE_POST + poster.getId(), new VolleyHandler() {
//            @Override
//            public void onSuccess(String response) {
//                Log.d("@@@onResponse ", response);
//            }
//
//            @Override
//            public void onError(String error) {
//                Log.d("@@@onErrorResponse ", error);
//            }
//        });
    }

    void workWithRetrofit() {

        RetrofitHttp.posterService.listPost().enqueue(new Callback<ArrayList<PosterResp>>() {
            @Override
            public void onResponse(Call<ArrayList<PosterResp>> call, Response<ArrayList<PosterResp>> response) {
                Log.d("@@@", response.body().toString());
            }

            @Override
            public void onFailure(Call<ArrayList<PosterResp>> call, Throwable t) {
                Log.d("@@@", t.toString());
            }
        });

        Poster poster = new Poster(1, 1, "PDP", "Online");
        RetrofitHttp.posterService.createPost(poster).enqueue(new Callback<PosterResp>() {
            @Override
            public void onResponse(Call<PosterResp> call, Response<PosterResp> response) {
                Log.d("@@@", response.body().toString());
            }

            @Override
            public void onFailure(Call<PosterResp> call, Throwable t) {
                Log.d("@@@", t.toString());
            }
        });

        RetrofitHttp.posterService.updatePost(poster.getId(), poster).enqueue(new Callback<PosterResp>() {
            @Override
            public void onResponse(Call<PosterResp> call, Response<PosterResp> response) {
                Log.d("@@@", response.body().toString());
            }

            @Override
            public void onFailure(Call<PosterResp> call, Throwable t) {
                Log.d("@@@", t.toString());
            }
        });

        RetrofitHttp.posterService.deletePost(poster.getId()).enqueue(new Callback<PosterResp>() {
            @Override
            public void onResponse(Call<PosterResp> call, Response<PosterResp> response) {
                Log.d("@@@", response.body().toString());
            }

            @Override
            public void onFailure(Call<PosterResp> call, Throwable t) {
                Log.d("@@@", t.toString());
            }
        });
    }
}
