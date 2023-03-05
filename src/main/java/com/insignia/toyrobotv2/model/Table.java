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
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component

public class Table implements CommandValidator {

    // private static variable to hold the singleton instance
    private static volatile Table instance;

    @JsonIgnore
    private int tableLength;

    @JsonIgnore
    private int tableBreadth;

    private Set<Robot> robots = new HashSet<>();

    @Autowired
    private Place place;

    @JsonProperty("activeRobotId")
    private int activeRobotId;


    // private constructor to prevent instantiation from outside
    private Table(int length, int breadth) {
        this.tableLength = length;
        this.tableBreadth = breadth;
    }


    // public static method to get the singleton instance

    public static Table getInstance(int length, int breadth) {


        if (instance == null) { // check if the instance is not already created
            synchronized (Table.class) { // acquire lock on the class object
                if (instance == null) { // double check for thread-safety
                    instance = new Table(length, breadth) {
                    }; // create the singleton instance
                }
            }
        }
        return instance; // return the singleton instance
    }


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
        Optional<Robot> optionalToyRobot = getRobots()
                .stream()
                .filter(r -> (r.getId() == id))
                .findFirst();

        if(optionalToyRobot.isEmpty())
            throw new GameException("Robot "+ id + " NOT FOUND");

            return optionalToyRobot.get();

    }


}
