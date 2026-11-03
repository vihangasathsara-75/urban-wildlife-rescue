package com.urbanwildlife.model;

import java.util.List;

public abstract class Animal {

    protected String animalType;

    public Animal(String animalType) {
        this.animalType = animalType;
    }

    public String getAnimalType() {
        return animalType;
    }

    public abstract List<String> getRequiredSkills();
}