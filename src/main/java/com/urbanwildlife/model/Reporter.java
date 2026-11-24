package com.urbanwildlife.model;

public class Reporter extends Person {

    public Reporter(String id, String name, String phone) {
        super(id, name, phone);
    }

    @Override
    public String getRole() {
        return "Reporter";
    }
}