package com.urbanwildlife.model;

public class RescueCoordinator extends Person {

    public RescueCoordinator(String id, String name, String phone) {
        super(id, name, phone);
    }

    @Override
    public String getRole() {
        return "Rescue Coordinator";
    }
}