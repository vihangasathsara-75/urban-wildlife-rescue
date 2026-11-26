package com.urbanwildlife.service;

import com.urbanwildlife.exceptions.IncidentNotFoundException;
import com.urbanwildlife.exceptions.InvalidInputException;
import com.urbanwildlife.model.Animal;
import com.urbanwildlife.model.Location;
import com.urbanwildlife.model.Reporter;
import com.urbanwildlife.model.RescueIncident;
import com.urbanwildlife.model.RescueTeam;
import java.util.ArrayList;
import java.util.List;

public class IncidentManager {

    private List<RescueIncident> incidents;
    private List<RescueTeam> teams;
    private PriorityCalculator calculator;
    private int nextIncidentNumber;

    public IncidentManager() {
        this.incidents = new ArrayList<>();
        this.teams = new ArrayList<>();
        this.calculator = new PriorityCalculator();
        this.nextIncidentNumber = 1;
    }

    public void addTeam(RescueTeam team) {
        teams.add(team);
    }

    // creates a new incident with the next id and works out its priority
    public RescueIncident reportIncident(Animal animal, Location location, Reporter reporter,
                                         int numberOfAnimals, boolean immediateDanger,
                                         String specialRequirements) throws InvalidInputException {
        String id = String.format("INC%03d", nextIncidentNumber);
        RescueIncident incident = new RescueIncident(id, animal, location, reporter,
                numberOfAnimals, immediateDanger, specialRequirements);
        calculator.assessPriority(incident);
        incidents.add(incident);
        nextIncidentNumber++;
        return incident;
    }

    public RescueIncident findIncident(String id) throws IncidentNotFoundException {
        for (RescueIncident incident : incidents) {
            if (incident.getId().equalsIgnoreCase(id)) {
                return incident;
            }
        }
        throw new IncidentNotFoundException("No incident found with id " + id);
    }

    public List<RescueIncident> getAllIncidents() {
        return incidents;
    }

    public List<RescueTeam> getAllTeams() {
        return teams;
    }
}