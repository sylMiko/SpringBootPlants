package com.client.playersfront.retrofit;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitSer {

    public Retrofit getRetrofit() {
        return retrofit;
    }

    private Retrofit retrofit;

    public RetrofitSer() { initRetrofit();}


    private void initRetrofit() {
        retrofit = new Retrofit.Builder().baseUrl("http://172.28.122.58:8080")
                .addConverterFactory(GsonConverterFactory.create(new Gson())).build();
    }
}
