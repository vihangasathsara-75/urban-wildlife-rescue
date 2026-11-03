package com.urbanwildlife.model;

import java.util.List;

public class Mammal extends Animal {

    public Mammal() {
        super("MAMMAL");
    }

    @Override
    public List<String> getRequiredSkills() {
        return List.of("Mammal Handling", "Animal Restraint");
    }
}