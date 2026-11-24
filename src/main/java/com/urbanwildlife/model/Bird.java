package com.urbanwildlife.model;

import com.urbanwildlife.enums.AnimalCondition;
import com.urbanwildlife.enums.AnimalType;
import java.util.List;

public class Bird extends Animal {

    public Bird(String species, AnimalCondition condition) {
        super(AnimalType.BIRD, species, condition);
    }

    @Override
    public List<String> getRequiredSkills() {
        return List.of("Bird Handling", "Tree Rescue");
    }
}