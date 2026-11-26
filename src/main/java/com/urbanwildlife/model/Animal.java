package com.urbanwildlife.model;

import com.urbanwildlife.enums.AnimalCondition;
import com.urbanwildlife.enums.AnimalType;
import java.util.List;

public abstract class Animal {

    private AnimalType type;
    private String species;
    private AnimalCondition condition;

    public Animal(AnimalType type, String species, AnimalCondition condition) {
        this.type = type;
        this.species = species;
        this.condition = condition;
    }

    public AnimalType getType() {
        return type;
    }

    public String getSpecies() {
        return species;
    }

    public AnimalCondition getCondition() {
        return condition;
    }

    public void setCondition(AnimalCondition condition) {
        this.condition = condition;
    }

    public abstract List<String> getRequiredSkills();

    public abstract int getHandlingRisk();
}