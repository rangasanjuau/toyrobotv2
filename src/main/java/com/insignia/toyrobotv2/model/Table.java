package com.insignia.toyrobotv2.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.insignia.toyrobotv2.commands.Place;
import com.insignia.toyrobotv2.exception.GameException;
import com.insignia.toyrobotv2.validation.CommandValidator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

@Data
@Component
public class Table implements CommandValidator {

    @Value("${table.length}")
    private int tableLength;

    @Value("${table.breadth}")
    private int tableBreadth;

    @Value("#{${robot.rotation}}")
    private LinkedHashMap<String, Integer> rotation;

    @Value("#{${robot.commands}}")
    private LinkedHashMap<String, Integer> commands;

    @Value("#{${robot.directions}}")
    private LinkedHashMap<String, List<Integer>> directionMap;

    private Set<Robot> robots = new HashSet<>();

    @JsonProperty("activeRobotId")
    private int activeRobotId;





    public boolean isNotOccupied(Position newPosition) {
        return robots.stream().anyMatch(e -> e.getPosition().getX() == newPosition.getX() && e.getPosition().getY() == newPosition.getY());
    }


    public boolean isOnTable(Position newPosition) {
        int x = newPosition.getX();
        int y = newPosition.getY();
        return x >= 0 && x < tableLength && y >= 0 && y < tableBreadth;
    }

    public Robot getRobotById(int id) throws GameException {
        // Get the Active Robot
        Optional<Robot> optionalToyRobot = robots
                .stream()
                .filter(r -> (r.getId() == id))
                .findFirst();

        if(optionalToyRobot.isEmpty())
            throw new GameException("Robot "+ id + " NOT FOUND");

            return optionalToyRobot.get();

    }


}
