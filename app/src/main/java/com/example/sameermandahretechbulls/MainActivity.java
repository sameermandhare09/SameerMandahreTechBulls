package com.example.sameermandahretechbulls;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {
    RecyclerView rv_movie;
    RecyclerView.LayoutManager  layoutManager;
    MovieAdapter movieAdapter;
    EditText editSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editSearch = findViewById(R.id.editSearch);
        layoutManager = new LinearLayoutManager(this);
        rv_movie = findViewById(R.id.rv_movie);
        rv_movie.setLayoutManager(layoutManager);


        apiCall();
        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                movieAdapter.getFilter().filter(charSequence.toString());

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }



    private void apiCall() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.omdbapi.com/")
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        APIInterface apiInterface =retrofit.create(APIInterface.class);
        Call<ApiResponse> call = apiInterface.getMovies("batman","a3cb9ba9");
        call.enqueue(new Callback<ApiResponse>() {

            @Override
            public void onResponse(Call<ApiResponse> call, retrofit2.Response<ApiResponse> response) {
                List<Search> movies =  response.body().getSearch();;
                movieAdapter = new MovieAdapter(getApplicationContext(),movies);
                rv_movie.setAdapter(movieAdapter);
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.e("error",t.getMessage());
                Toast.makeText(MainActivity.this, "Internet problem ", Toast.LENGTH_SHORT).show();

            }
        });




    }

}