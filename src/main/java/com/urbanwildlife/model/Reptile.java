package com.urbanwildlife.model;

import java.util.List;

public class Reptile extends Animal {

    public Reptile() {
        super("REPTILE");
    }

    @Override
    public List<String> getRequiredSkills() {
        return List.of("Reptile Handling", "Safe Capture");
    }
}