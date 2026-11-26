package com.urbanwildlife;

import com.urbanwildlife.enums.AnimalCondition;
import com.urbanwildlife.enums.IncidentStatus;
import com.urbanwildlife.enums.LocationRisk;
import com.urbanwildlife.enums.TeamStatus;
import com.urbanwildlife.exceptions.InvalidInputException;
import com.urbanwildlife.exceptions.NoAvailableTeamException;
import com.urbanwildlife.model.*;
import com.urbanwildlife.service.PriorityCalculator;
import com.urbanwildlife.service.TeamMatcher;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

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

        System.out.println(team);
        System.out.println("Equipment: " + team.getEquipment());

        // making an incident
        Animal bird = new Bird("Owl", AnimalCondition.TRAPPED);
        Location location = new Location("12 Galle Road", "Colombo 03", LocationRisk.MEDIUM);
        Reporter reporter = new Reporter("R001", "Saman", "0712223334");

        try {
            RescueIncident incident = new RescueIncident("INC001", bird, location, reporter,
                    1, true, "Stuck in a tall tree");
            System.out.println(incident);

            // working out the priority
            PriorityCalculator calculator = new PriorityCalculator();
            calculator.assessPriority(incident);
            System.out.println("Score: " + incident.getPriorityScore());
            System.out.println(incident);

            // finding a team for the incident
            List<RescueTeam> teams = new ArrayList<>();
            teams.add(team);

            TeamMatcher matcher = new TeamMatcher();
            List<RescueTeam> suitableTeams = matcher.findSuitableTeams(incident, teams);
            System.out.println("Suitable teams: " + suitableTeams);

            RescueTeam chosenTeam = suitableTeams.get(0);
            incident.setAssignedTeam(chosenTeam);
            incident.setStatus(IncidentStatus.ASSIGNED);
            chosenTeam.setStatus(TeamStatus.ASSIGNED);
            System.out.println(incident);
            System.out.println("Assigned to: " + incident.getAssignedTeam().getName());

            // a snake incident - Alpha Team is busy now and has no reptile skills
            Animal snake = new Reptile("Cobra", AnimalCondition.DISPLACED);
            RescueIncident snakeIncident = new RescueIncident("INC003", snake, location, reporter,
                    1, false, "In a garden");
            matcher.findSuitableTeams(snakeIncident, teams);
        } catch (InvalidInputException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (NoAvailableTeamException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // testing the exception with 0 animals
        try {
            new RescueIncident("INC002", bird, location, reporter, 0, false, "");
        } catch (InvalidInputException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}