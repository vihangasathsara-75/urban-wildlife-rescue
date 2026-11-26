package com.urbanwildlife.model;

import com.urbanwildlife.enums.AnimalCondition;
import com.urbanwildlife.enums.AnimalType;
import java.util.List;

public class OtherAnimal extends Animal {

    public OtherAnimal(String species, AnimalCondition condition) {
        super(AnimalType.OTHER, species, condition);
    }

    @Override
    public List<String> getRequiredSkills() {
        return List.of("General Animal Handling");
    }

    @Override
    public int getHandlingRisk() {
        return 2;
    }
}