package com.example.sameermandahretechbulls;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIInterface {

//    @Headers({"Content-type: application/json"})

    @GET(" ")//s=batman&apikey=a3cb9ba9
    Call<ApiResponse> getMovies(@Query("s") String a,
                                @Query("apikey") String b);




}
