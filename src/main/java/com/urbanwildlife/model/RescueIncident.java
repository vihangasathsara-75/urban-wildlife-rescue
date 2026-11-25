package com.urbanwildlife.model;

import com.urbanwildlife.enums.IncidentStatus;
import com.urbanwildlife.enums.PriorityLevel;
import com.urbanwildlife.exceptions.InvalidInputException;
import java.time.LocalDateTime;

public class RescueIncident {

    private String id;
    private Animal animal;
    private Location location;
    private Reporter reporter;
    private int numberOfAnimals;
    private boolean immediateDanger;
    private String specialRequirements;
    private LocalDateTime reportedTime;
    private IncidentStatus status;
    private PriorityLevel priority;
    private int priorityScore;

    public RescueIncident(String id, Animal animal, Location location, Reporter reporter,
                          int numberOfAnimals, boolean immediateDanger,
                          String specialRequirements) throws InvalidInputException {
        if (numberOfAnimals < 1) {
            throw new InvalidInputException("Number of animals must be at least 1");
        }
        this.id = id;
        this.animal = animal;
        this.location = location;
        this.reporter = reporter;
        this.numberOfAnimals = numberOfAnimals;
        this.immediateDanger = immediateDanger;
        this.specialRequirements = specialRequirements;
        this.reportedTime = LocalDateTime.now();
        this.status = IncidentStatus.REPORTED;
    }

    public String getId() {
        return id;
    }

    public Animal getAnimal() {
        return animal;
    }

    public Location getLocation() {
        return location;
    }

    public Reporter getReporter() {
        return reporter;
    }

    public int getNumberOfAnimals() {
        return numberOfAnimals;
    }

    public boolean isImmediateDanger() {
        return immediateDanger;
    }

    public String getSpecialRequirements() {
        return specialRequirements;
    }

    public LocalDateTime getReportedTime() {
        return reportedTime;
    }

    public IncidentStatus getStatus() {
        return status;
    }

    public void setStatus(IncidentStatus status) {
        this.status = status;
    }

    public PriorityLevel getPriority() {
        return priority;
    }

    public int getPriorityScore() {
        return priorityScore;
    }

    public void setPriority(PriorityLevel priority, int score) {
        this.priority = priority;
        this.priorityScore = score;
    }

    @Override
    public String toString() {
        return id + " | " + animal.getSpecies() + " x" + numberOfAnimals
                + " | " + location.getArea() + " | " + status
                + " | Priority: " + (priority == null ? "Not set" : priority);
    }
}