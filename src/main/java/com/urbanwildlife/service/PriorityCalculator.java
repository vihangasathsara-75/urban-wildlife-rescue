package com.urbanwildlife.service;

import com.urbanwildlife.enums.AnimalCondition;
import com.urbanwildlife.enums.IncidentStatus;
import com.urbanwildlife.enums.LocationRisk;
import com.urbanwildlife.enums.PriorityLevel;
import com.urbanwildlife.exceptions.InvalidInputException;
import com.urbanwildlife.model.RescueIncident;

public class PriorityCalculator {

    // score = 3 x condition + 2 x danger + 2 x countBand + 2 x locationRisk + handlingRisk
    public int calculateScore(RescueIncident incident) throws InvalidInputException {
        if (incident.getAnimal() == null || incident.getLocation() == null) {
            throw new InvalidInputException("Incident is missing animal or location details");
        }

        int conditionPoints = getConditionPoints(incident);
        int dangerPoints = getDangerPoints(incident);
        int countPoints = getCountPoints(incident);
        int locationPoints = getLocationPoints(incident);
        int handlingPoints = incident.getAnimal().getHandlingRisk();

        return 3 * conditionPoints + 2 * dangerPoints + 2 * countPoints
                + 2 * locationPoints + handlingPoints;
    }

    public PriorityLevel getLevel(int score) {
        if (score >= 27) {
            return PriorityLevel.CRITICAL;
        } else if (score >= 21) {
            return PriorityLevel.HIGH;
        } else if (score >= 16) {
            return PriorityLevel.MEDIUM;
        } else {
            return PriorityLevel.LOW;
        }
    }

    // works out the score and level and saves them in the incident
    public void assessPriority(RescueIncident incident) throws InvalidInputException {
        int score = calculateScore(incident);
        PriorityLevel level = getLevel(score);
        incident.setPriority(level, score);
        incident.setStatus(IncidentStatus.PENDING_ASSIGNMENT);
    }

    private int getConditionPoints(RescueIncident incident) {
        AnimalCondition condition = incident.getAnimal().getCondition();
        if (condition == AnimalCondition.TRAPPED) {
            return 5;
        } else if (condition == AnimalCondition.INJURED) {
            return 4;
        } else if (condition == AnimalCondition.DISPLACED) {
            return 3;
        } else {
            return 2; // ABANDONED
        }
    }

    private int getDangerPoints(RescueIncident incident) {
        if (incident.isImmediateDanger()) {
            return 3;
        }
        return 1;
    }

    private int getCountPoints(RescueIncident incident) {
        int count = incident.getNumberOfAnimals();
        if (count >= 4) {
            return 3;
        } else if (count >= 2) {
            return 2;
        }
        return 1;
    }

    private int getLocationPoints(RescueIncident incident) {
        LocationRisk risk = incident.getLocation().getRisk();
        if (risk == LocationRisk.HIGH) {
            return 3;
        } else if (risk == LocationRisk.MEDIUM) {
            return 2;
        } else {
            return 1; // LOW
        }
    }
}