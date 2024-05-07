package com.example.mypokemonapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface JsonPlaceholderApi {

    @GET
    Call<PokemonResponse> getPokemonResponse(@Url String url);

    @GET
    Call<PokemonInfo> getPokemonInfo(@Url String url);


}