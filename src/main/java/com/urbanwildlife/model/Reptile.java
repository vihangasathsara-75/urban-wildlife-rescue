package com.urbanwildlife.model;

import com.urbanwildlife.enums.AnimalCondition;
import com.urbanwildlife.enums.AnimalType;
import java.util.List;

public class Reptile extends Animal {

    public Reptile(String species, AnimalCondition condition) {
        super(AnimalType.REPTILE, species, condition);
    }

    @Override
    public List<String> getRequiredSkills() {
        return List.of("Reptile Handling", "Safe Capture");
    }
}