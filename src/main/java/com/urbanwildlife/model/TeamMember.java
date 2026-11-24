package com.urbanwildlife.model;

import java.util.ArrayList;
import java.util.List;

public class TeamMember extends Person {

    private List<String> skills;
    private boolean available;

    public TeamMember(String id, String name, String phone) {
        super(id, name, phone);
        this.skills = new ArrayList<>();
        this.available = true;
    }

    public void addSkill(String skill) {
        skills.add(skill);
    }

    public boolean hasSkill(String skill) {
        return skills.contains(skill);
    }

    public List<String> getSkills() {
        return skills;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String getRole() {
        return "Team Member";
    }
}