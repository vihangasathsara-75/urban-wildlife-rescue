package com.urbanwildlife.model;

import com.urbanwildlife.enums.AnimalCondition;
import com.urbanwildlife.enums.AnimalType;
import java.util.List;

public class Mammal extends Animal {

    public Mammal(String species, AnimalCondition condition) {
        super(AnimalType.MAMMAL, species, condition);
    }

    @Override
    public List<String> getRequiredSkills() {
        return List.of("Mammal Handling", "Animal Restraint");
    }

    @Override
    public int getHandlingRisk() {
        return 2;
    }
}