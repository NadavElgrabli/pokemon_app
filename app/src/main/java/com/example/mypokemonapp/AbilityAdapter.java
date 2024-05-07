package com.example.mypokemonapp;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AbilityAdapter extends RecyclerView.Adapter<AbilityAdapter.AbilityViewHolder>{

private List<Ability> abilities;

    public AbilityAdapter(List<Ability> abilities) {
        this.abilities = abilities;
    }


    @Override
    public int getItemCount() {
        return abilities.size();
    }
    @NonNull
    @Override
    public AbilityAdapter.AbilityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_ability, null);
        return new AbilityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AbilityAdapter.AbilityViewHolder holder, int position) {
        holder.bind(abilities.get(position));

    }




    static class AbilityViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewAbilityName;
        public AbilityViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewAbilityName = itemView.findViewById(R.id.text_view_ability_name);
        }

        public void bind(Ability ability) {
            textViewAbilityName.setText(ability.getName());
        }
    }
}
