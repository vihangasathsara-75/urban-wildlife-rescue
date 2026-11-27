package com.urbanwildlife.service;

import com.urbanwildlife.exceptions.IncidentNotFoundException;
import com.urbanwildlife.exceptions.InvalidInputException;
import com.urbanwildlife.model.Animal;
import com.urbanwildlife.model.Location;
import com.urbanwildlife.model.Reporter;
import com.urbanwildlife.model.RescueIncident;
import com.urbanwildlife.model.RescueTeam;
import com.urbanwildlife.enums.IncidentStatus;
import com.urbanwildlife.enums.TeamStatus;
import com.urbanwildlife.exceptions.NoAvailableTeamException;
import java.util.ArrayList;
import java.util.List;

public class IncidentManager {

    private List<RescueIncident> incidents;
    private List<RescueTeam> teams;
    private PriorityCalculator calculator;
    private TeamMatcher matcher;
    private int nextIncidentNumber;


    public IncidentManager() {
        this.incidents = new ArrayList<>();
        this.teams = new ArrayList<>();
        this.calculator = new PriorityCalculator();
        this.nextIncidentNumber = 1;
        this.matcher = new TeamMatcher();
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

    // finds teams that are free and have the right skills for this incident
    public List<RescueTeam> findTeamsForIncident(String incidentId)
            throws IncidentNotFoundException, NoAvailableTeamException {
        RescueIncident incident = findIncident(incidentId);
        return matcher.findSuitableTeams(incident, teams);
    }

    // gives the incident to a team and updates both statuses
    public void assignTeam(String incidentId, RescueTeam team)
            throws IncidentNotFoundException, InvalidInputException {
        RescueIncident incident = findIncident(incidentId);

        if (incident.getStatus() != IncidentStatus.PENDING_ASSIGNMENT) {
            throw new InvalidInputException("Incident " + incidentId + " is not waiting for a team");
        }
        if (!team.isAvailable()) {
            throw new InvalidInputException(team.getName() + " is not available");
        }

        incident.setAssignedTeam(team);
        incident.setStatus(IncidentStatus.ASSIGNED);
        team.setStatus(TeamStatus.ASSIGNED);
    }

    // moves the incident to its next stage and frees the team when it is done
    public void updateStatus(String incidentId, IncidentStatus newStatus)
            throws IncidentNotFoundException, InvalidInputException {
        RescueIncident incident = findIncident(incidentId);
        IncidentStatus current = incident.getStatus();

        if (newStatus == IncidentStatus.IN_PROGRESS) {
            if (current != IncidentStatus.ASSIGNED) {
                throw new InvalidInputException("Incident " + incidentId + " must be assigned before it can start");
            }
            incident.setStatus(IncidentStatus.IN_PROGRESS);
        } else if (newStatus == IncidentStatus.COMPLETED) {
            if (current != IncidentStatus.IN_PROGRESS) {
                throw new InvalidInputException("Incident " + incidentId + " must be in progress before it can be completed");
            }
            incident.setStatus(IncidentStatus.COMPLETED);
            incident.getAssignedTeam().setStatus(TeamStatus.AVAILABLE);
        } else {
            throw new InvalidInputException("Status can only be changed to IN_PROGRESS or COMPLETED");
        }
    }

    public List<RescueTeam> getAllTeams() {
        return teams;
    }
}