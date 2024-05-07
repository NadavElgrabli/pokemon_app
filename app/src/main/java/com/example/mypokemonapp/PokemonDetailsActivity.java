package com.example.mypokemonapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonDetailsActivity extends AppCompatActivity {

    private TextView textViewPokemonName;
    private TextView textViewPokemonHeight;
    private TextView textViewPokemonWeight;
    private RecyclerView recyclerViewPokemonAbilities;
    private Button buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_details);
        findViewsById();
        setOnClickListener();

        // Get the URL passed from MainActivity
        String pokemonUrl = getIntent().getStringExtra("pokemonUrl");
        fetchPokemonInfo(pokemonUrl);
    }

    private void findViewsById() {
        textViewPokemonName = findViewById(R.id.text_view_pokemon_name);
        textViewPokemonHeight = findViewById(R.id.text_view_pokemon_height);
        textViewPokemonWeight = findViewById(R.id.text_view_pokemon_weight);
        recyclerViewPokemonAbilities = findViewById(R.id.recycler_view_pokemon_abilities);
        buttonBack = findViewById(R.id.button_back_to_main_menu);
    }

    private void setOnClickListener() {
        buttonBack.setOnClickListener(v -> {
            Intent intent = new Intent(PokemonDetailsActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    private void fetchPokemonInfo(String pokemonUrl) {
        JsonPlaceholderApi jsonPlaceholderApi = ApiClient.getClient().create(JsonPlaceholderApi.class);
        Call<PokemonInfo> call = jsonPlaceholderApi.getPokemonInfo(pokemonUrl);
        call.enqueue(new Callback<PokemonInfo>() {
            @Override
            public void onResponse(Call<PokemonInfo> call, Response<PokemonInfo> response) {
                if (response.isSuccessful() && response.body() != null) {
                    PokemonInfo pokemonInfo = response.body();
                    displayPokemonInfo(pokemonInfo);
                } else {
                    Toast.makeText(PokemonDetailsActivity.this, "Failed to fetch pokemon info", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PokemonInfo> call, Throwable t) {
                Log.e("PokemonDetailsActivity", "Failed to fetch pokemon info: " + t.getMessage());
                Toast.makeText(PokemonDetailsActivity.this, "Failed to fetch pokemon info", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void displayPokemonInfo(PokemonInfo pokemonInfo) {
        textViewPokemonName.setText(pokemonInfo.getName());
        textViewPokemonHeight.setText("Height: " + pokemonInfo.getHeight());
        textViewPokemonWeight.setText("Weight: " + pokemonInfo.getWeight());

        // Display abilities using RecyclerView
        List<Ability> abilities = new ArrayList<>();
        for (AbilityResponse abilityResponse : pokemonInfo.getAbilities()) {
            abilities.add(abilityResponse.getAbility());
        }
        AbilityAdapter abilityAdapter = new AbilityAdapter(abilities);
        recyclerViewPokemonAbilities.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewPokemonAbilities.setAdapter(abilityAdapter);
    }
}
