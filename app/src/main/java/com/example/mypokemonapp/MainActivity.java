package com.example.mypokemonapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewPokemons;
    private JsonPlaceholderApi jsonPlaceholderApi;
    private EditText editTextPokemonName;
    private Button buttonSearchPokemonByName;
    private List<Pokemon> allPokemons;
    private PokemonAdapter pokemonAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewsById();
        setOnClickListeners();
        jsonPlaceholderApi = ApiClient.getClient().create(JsonPlaceholderApi.class);
        fetchPokemons();
    }

    public void findViewsById() {
        recyclerViewPokemons = findViewById(R.id.recycler_view_pokemons);
        editTextPokemonName = findViewById(R.id.edit_text_pokemon_name);
        buttonSearchPokemonByName = findViewById(R.id.button_search_pokemon_by_name);
    }

    public void setOnClickListeners() {
        buttonSearchPokemonByName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchPokemonByName();
            }
        });
    }

    private void searchPokemonByName() {
        String query = editTextPokemonName.getText().toString().trim().toLowerCase();
        if (query.isEmpty()) {
            // If the query is empty, show all pokemons
            setRecyclerViewAdapter(allPokemons);
        } else {
            List<Pokemon> searchResult = new ArrayList<>();
            boolean found = false;
            for (Pokemon pokemon : allPokemons) {
                if (pokemon.getName().toLowerCase().equals(query)) { // Compare in lowercase
                    searchResult.add(pokemon);
                    found = true;
                }
            }
            if (found) {
                setRecyclerViewAdapter(searchResult);
            } else {
                // If no pokemon matches the query, show a toast message
                Toast.makeText(this, "Pokemon doesn't exist", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void fetchPokemons() {
        allPokemons = new ArrayList<>();
        Call<PokemonResponse> call = jsonPlaceholderApi.getPokemonResponse("https://pokeapi.co/api/v2/pokemon");
        call.enqueue(new Callback<PokemonResponse>() {
            @Override
            public void onResponse(Call<PokemonResponse> call, Response<PokemonResponse> response) {
                if (!response.isSuccessful()) {
                    Log.e("MainActivity", "Failed to fetch pokemons: " + response.code());
                    return;
                }

                PokemonResponse pokemonResponse = response.body();
                if (pokemonResponse != null) {
                    // On successful response, extract pokemon data and get the information from the URL
                    List<Pokemon> pokemonList = pokemonResponse.getResults();
                    allPokemons.addAll(pokemonList);
                    // Set the RecyclerView adapter
                    setRecyclerViewAdapter(allPokemons);
                }
            }

            @Override
            public void onFailure(Call<PokemonResponse> call, Throwable t) {
                Log.e("MainActivity", "Failed to fetch pokemons: " + t.getMessage());
                Toast.makeText(MainActivity.this, "Couldn't get information", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setRecyclerViewAdapter(List<Pokemon> pokemonList) {
        // Create the adapter with the list of pokemons
        pokemonAdapter = new PokemonAdapter(pokemonList, MainActivity.this);

        // Set the adapter to the RecyclerView
        recyclerViewPokemons.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewPokemons.setAdapter(pokemonAdapter);
    }
}
