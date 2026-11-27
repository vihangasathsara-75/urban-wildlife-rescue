package com.urbanwildlife;

import com.urbanwildlife.enums.AnimalCondition;
import com.urbanwildlife.enums.IncidentStatus;
import com.urbanwildlife.enums.LocationRisk;
import com.urbanwildlife.exceptions.IncidentNotFoundException;
import com.urbanwildlife.exceptions.InvalidInputException;
import com.urbanwildlife.exceptions.NoAvailableTeamException;
import com.urbanwildlife.model.*;
import com.urbanwildlife.service.IncidentManager;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        IncidentManager manager = new IncidentManager();

        // making a team
        TeamMember nimal = new TeamMember("M001", "Nimal", "0771234567");
        nimal.addSkill("Bird Handling");
        nimal.addSkill("Tree Rescue");

        TeamMember kasun = new TeamMember("M002", "Kasun", "0779876543");
        kasun.addSkill("Mammal Handling");

        RescueTeam team = new RescueTeam("T001", "Alpha Team");
        team.addMember(nimal);
        team.addMember(kasun);
        team.addEquipment(new Equipment("E001", "Ladder"));
        team.addEquipment(new Equipment("E002", "Bird Net"));
        manager.addTeam(team);
        System.out.println(team);

        Location location = new Location("12 Galle Road", "Colombo 03", LocationRisk.MEDIUM);
        Reporter reporter = new Reporter("R001", "Saman", "0712223334");

        try {
            // reporting an incident - the manager gives it an id and a priority
            Animal owl = new Bird("Owl", AnimalCondition.TRAPPED);
            RescueIncident incident = manager.reportIncident(owl, location, reporter,
                    1, true, "Stuck in a tall tree");
            System.out.println(incident);

            // finding and assigning a team
            List<RescueTeam> suitableTeams = manager.findTeamsForIncident(incident.getId());
            System.out.println("Suitable teams: " + suitableTeams);
            manager.assignTeam(incident.getId(), suitableTeams.get(0));
            System.out.println(incident);
            System.out.println(team);

            // the team starts and finishes the rescue
            manager.updateStatus(incident.getId(), IncidentStatus.IN_PROGRESS);
            System.out.println(incident);
            manager.updateStatus(incident.getId(), IncidentStatus.COMPLETED);
            System.out.println(incident);
            System.out.println(team);
        } catch (InvalidInputException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (IncidentNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (NoAvailableTeamException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // a snake - no team has reptile skills
        try {
            Animal snake = new Reptile("Cobra", AnimalCondition.DISPLACED);
            RescueIncident snakeIncident = manager.reportIncident(snake, location, reporter,
                    1, false, "In a garden");
            manager.findTeamsForIncident(snakeIncident.getId());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        // completing an incident that has not started
        try {
            manager.updateStatus("INC002", IncidentStatus.COMPLETED);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        // searching for an incident that does not exist
        try {
            manager.findIncident("INC099");
        } catch (IncidentNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // testing 0 animals
        try {
            manager.reportIncident(new Bird("Crow", AnimalCondition.INJURED), location, reporter,
                    0, false, "");
        } catch (InvalidInputException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}