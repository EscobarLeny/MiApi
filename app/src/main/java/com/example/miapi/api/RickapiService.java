package com.example.miapi.api;

public interface RickapiService {

    @GET("rickandmorty")
    Call <RickandmortyRespuesta> obtenerListaRickandmorty();
}
