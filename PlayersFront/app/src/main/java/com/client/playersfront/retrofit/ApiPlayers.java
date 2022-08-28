package com.client.playersfront.retrofit;

import com.client.playersfront.model.Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiPlayers {
    @GET("/player/getAll")
    Call<List<Model>> getAllModels();

    @POST("/player/save")
    Call<Model> save(@Body Model player);

    @GET("/player/{id}")
    Call<Model> findById(@Path("id") long id);

    @PUT("/player/update/{id}")
    Call<Model> update(@Body Model player, @Path("id") long id);

    @DELETE("/player/delete/{id}")
    Call<Void> delete(@Path("id") long id);
}
