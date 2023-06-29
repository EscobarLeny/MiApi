package com.example.miapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Window;

import com.example.miapi.api.RickapiService;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ListaRickandmorty listaRickandmorty;
    Retrofit retrofit;

    private static final String TAG ="RickAndMorty";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.card_recycler_view);
        listaRickandmortyAdapter=new ListaRickandmortyAdapter(this);
        recyclerView.setAdapter(listaRickandmorty);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,recyclerView.VERTICAL,true);
        recyclerView.setLayoutManager(linearLayoutManager);

        retrofit = new Retrofit.Builder()
                .baseurl("https://rickandmortyapi.com/api")
                .addCoverterFactory(GsonConverterFactory.create())
                .build();



    }

    private void obtenerDatos2(){
        RickapiService service = retrofit.create(RickapiService.class);
        Call<RickandmortyRespuesta> rickandmortyRespuestaCall = service.obtenerListaRickandmorty();

        rickandmortyRespuestaCall.enqueue(new Callback<RickandmortyRespuesta>(){
            @Override
            public void onResponse(Call<RickandmortyRespuesta> call, Response<RickandmortyRespuesta> response){

                if (response.isSuccessful()){
                    RickandmortyRespuesta rickandmortyRespuesta = response.body();
                    List<Rickandmorty> listaRickandmorty = rickandmortyRespuesta.getResults();
                    for (int i = 0; i <listaRickandmorty.size();i++){
                        Rickandmorty r = listaRickandmorty.get(i);
                        Log.e(TAG, "rickandmorty:" + r.getName());
                    }
                    listaRickandmortyAdapter.add((ArrayList<Rickandmorty>) listaRickandmorty);
                }
                else{
                    Log.e(TAG, "onResponse:" + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<Rickandmorty> call, Throwable t) {
                 Log.e(TAG, "onFAILURE:" + t.getMessage());
            }
        });


    }

    private void obtenerDatos(){
        RickapiService service = retrofit.create(RickapiService.class);
        Call<RickandmortyRespuesta> rickandmortyRespuestaCall = service.obtenerListaRickandmorty();
        rickandmortyRespuestaCall.enqueue(new Callback<RickandmortyRespuesta>(){

            @Override
            public void onResponse(Call<RickandmortyRespuesta> call, Response<RickandmortyRespuesta> response) {
                if (response.isSuccessful()) {
                    RickandmortyRespuesta rickandmortyRespuesta = response.body();
                    List<Rickandmorty> listaRickandmorty = rickandmortyRespuesta.getResults();
                    for (int i = 0; i < listaRickandmorty.size(); i++) {
                        Rickandmorty p = listaRickandmorty.get(i);
                        Log.e(TAG, "rickandmorty: " + p.getName());
                    }

                    listaRickandmortyAdapter.add((ArrayList<Rickandmorty>) listaRickandmorty);
                }
                else{
                    Log.e(TAG, "onResponse: " + response.errorBody());
                }
            }
            @Override
            public void onFailure(Call<RickandmortyRespuesta> call, Throwable t) {

                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });

    }
}
