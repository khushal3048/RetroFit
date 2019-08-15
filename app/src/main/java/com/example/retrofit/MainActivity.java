package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ArrayList<Pokemon> pokemons;
    Recyadapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Getdataservice service = RetroFitInstance.getRetrofitInstance().create(Getdataservice.class);

        // Response Comes As Json Array

        /*Call<List<Pokemon>> call = service.getPokemons();

        call.enqueue(new Callback<List<Pokemon>>() {
            @Override
            public void onResponse(Call<List<Pokemon>> call, Response<List<Pokemon>> response) {

                System.out.println(response.body());
                initView(response.body());

            }

            @Override
            public void onFailure(Call<List<Pokemon>> call, Throwable t) {

                Toast.makeText(getApplicationContext(),"Something Went Wrong!!!",Toast.LENGTH_SHORT).show();

            }
        });*/

        // Response Comes As JSON Object

        Call<Pokemonpojo> call = service.getPokemonsObj();

        call.enqueue(new Callback<Pokemonpojo>() {
            @Override
            public void onResponse(Call<Pokemonpojo> call, Response<Pokemonpojo> response) {

               Pokemonpojo pokemonpojo = response.body();

               try{

                   pokemons = new ArrayList<>(pokemonpojo.getPokemon());
                   initView(pokemons);


               }catch (NullPointerException e){

                   System.out.println(e.getMessage());

               }

            }

            @Override
            public void onFailure(Call<Pokemonpojo> call, Throwable t) {

                Toast.makeText(getApplicationContext(), "Something Went Wrong!!!", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void initView(List<Pokemon> poklist){

        //ArrayList<Pokemon> pokemons = (ArrayList<Pokemon>) poklist;
        adapter = new Recyadapter(pokemons,getApplicationContext());
        @SuppressLint("WrongConstant") LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        RecyclerView recyclerView = findViewById(R.id.recycle_poke);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        //adapter.setOnClickListener(onClickListener);
    }

    /*public View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();

            int position = viewHolder.getAdapterPosition();

            //Toast.makeText(getApplicationContext(),pokelst.get(position).getName(),Toast.LENGTH_SHORT).show();

            Intent i = new Intent(MainActivity.this,PokemonDesc.class);
            i.putExtra("data",pokemons.get(position));
            startActivity(i);

        }
    };*/


}
