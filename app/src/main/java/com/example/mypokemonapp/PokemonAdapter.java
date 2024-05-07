package com.example.mypokemonapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder> {

    private List<Pokemon> pokemons;
    private Context context;

    public PokemonAdapter(List<Pokemon> pokemons, Context context) {
        this.pokemons = pokemons;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return pokemons.size();
    }

    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon, parent, false);
        return new PokemonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonViewHolder holder, int position) {
        holder.bind(pokemons.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    Pokemon clickedPokemon = pokemons.get(adapterPosition);
                    Intent intent = new Intent(context, PokemonDetailsActivity.class);
                    intent.putExtra("pokemonUrl", clickedPokemon.getUrl());
                    context.startActivity(intent);
                }
            }
        });
    }

    static class PokemonViewHolder extends RecyclerView.ViewHolder {

        private TextView pokemonName;

        PokemonViewHolder(@NonNull View itemView) {
            super(itemView);
            pokemonName = itemView.findViewById(R.id.text_view_pokemon_name);
        }

        public void bind(Pokemon pokemon) {
            pokemonName.setText(pokemon.getName());
        }
    }
}

// WIHTOUT STATIC CLASS
//package com.example.mypokemonapp;
//
//import android.content.Context;
//import android.content.Intent;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.List;
//
//public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder> {
//
//    private List<Pokemon> pokemons;
//    private Context context;
//
//    public PokemonAdapter(List<Pokemon> pokemons, Context context) {
//        this.pokemons = pokemons;
//        this.context = context;
//    }
//
//    @Override
//    public int getItemCount() {
//        return pokemons.size();
//    }
//
//    @NonNull
//    @Override
//    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon, parent, false);
//        return new PokemonViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull PokemonViewHolder holder, int position) {
//        holder.bind(pokemons.get(position));
//    }
//
//    class PokemonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//
//        private TextView pokemonName;
//
//        public PokemonViewHolder(@NonNull View itemView) {
//            super(itemView);
//            pokemonName = itemView.findViewById(R.id.text_view_pokemon_name);
//            itemView.setOnClickListener(this);
//        }
//
//        public void bind(Pokemon pokemon) {
//            pokemonName.setText(pokemon.getName());
//        }
//
//        @Override
//        public void onClick(View v) {
//            int position = getAdapterPosition();
//            if (position != RecyclerView.NO_POSITION) {
//                Pokemon clickedPokemon = pokemons.get(position);
//                Intent intent = new Intent(context, PokemonDetailsActivity.class);
//                intent.putExtra("pokemonUrl", clickedPokemon.getUrl());
//                context.startActivity(intent);
//            }
//        }
//    }
//}