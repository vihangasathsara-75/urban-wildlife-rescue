package com.urbanwildlife.model;

import java.util.List;

public class OtherAnimal extends Animal {

    public OtherAnimal() {
        super("OTHER");
    }

    @Override
    public List<String> getRequiredSkills() {
        return List.of("General Animal Handling");
    }
}