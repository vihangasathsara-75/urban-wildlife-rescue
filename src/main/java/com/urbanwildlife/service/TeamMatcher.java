package com.urbanwildlife.service;

import com.urbanwildlife.exceptions.NoAvailableTeamException;
import com.urbanwildlife.model.RescueIncident;
import com.urbanwildlife.model.RescueTeam;
import java.util.ArrayList;
import java.util.List;

public class TeamMatcher {

    // returns all teams that are free and have every skill the animal needs
    public List<RescueTeam> findSuitableTeams(RescueIncident incident, List<RescueTeam> teams)
            throws NoAvailableTeamException {
        List<String> requiredSkills = incident.getAnimal().getRequiredSkills();
        List<RescueTeam> suitableTeams = new ArrayList<>();

        for (RescueTeam team : teams) {
            if (team.isAvailable() && team.hasAllSkills(requiredSkills)) {
                suitableTeams.add(team);
            }
        }

        if (suitableTeams.isEmpty()) {
            throw new NoAvailableTeamException("No available team has the skills for incident "
                    + incident.getId());
        }
        return suitableTeams;
    }
}