package com.urbanwildlife.model;

import java.util.List;

public class Bird extends Animal {

    public Bird() {
        super("BIRD");
    }

    @Override
    public List<String> getRequiredSkills() {
        return List.of("Bird Handling", "Tree Rescue");
    }
}