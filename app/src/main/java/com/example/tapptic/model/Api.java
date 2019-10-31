package com.example.tapptic.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    String BASE_URL = "http://dev.tapptic.com/";

    @GET("test/json.php")
    Call<List<NumberLight>> getNumbers();

    @GET("test/json.php?name=")
    Call<NumberLightDetails> getNumberDetails(@Query("name")String name);
}
