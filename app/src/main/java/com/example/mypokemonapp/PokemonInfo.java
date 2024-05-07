package com.example.mypokemonapp;

import java.util.List;

public class PokemonInfo {
    private String name;
    private int height;
    private int weight;
    private List<AbilityResponse> abilities;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }


    public List<AbilityResponse> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<AbilityResponse> abilities) {
        this.abilities = abilities;
    }
}
