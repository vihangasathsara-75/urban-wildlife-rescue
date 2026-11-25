package com.urbanwildlife.model;

import com.urbanwildlife.enums.TeamStatus;
import java.util.ArrayList;
import java.util.List;

public class RescueTeam {

    private String id;
    private String name;
    private List<TeamMember> members;
    private List<Equipment> equipment;
    private TeamStatus status;

    public RescueTeam(String id, String name) {
        this.id = id;
        this.name = name;
        this.members = new ArrayList<>();
        this.equipment = new ArrayList<>();
        this.status = TeamStatus.AVAILABLE;
    }

    public void addMember(TeamMember member) {
        members.add(member);
    }

    public void addEquipment(Equipment item) {
        equipment.add(item);
    }

    // checks if at least one member in the team has this skill
    public boolean hasSkill(String skill) {
        for (TeamMember member : members) {
            if (member.hasSkill(skill)) {
                return true;
            }
        }
        return false;
    }

    // checks if the team can handle all the skills an animal needs
    public boolean hasAllSkills(List<String> requiredSkills) {
        for (String skill : requiredSkills) {
            if (!hasSkill(skill)) {
                return false;
            }
        }
        return true;
    }

    public boolean isAvailable() {
        return status == TeamStatus.AVAILABLE;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<TeamMember> getMembers() {
        return members;
    }

    public List<Equipment> getEquipment() {
        return equipment;
    }

    public TeamStatus getStatus() {
        return status;
    }

    public void setStatus(TeamStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return id + " | " + name + " | " + members.size() + " members | " + status;
    }
}