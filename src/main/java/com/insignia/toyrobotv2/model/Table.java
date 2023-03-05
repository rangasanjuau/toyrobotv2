package com.insignia.toyrobotv2.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.insignia.toyrobotv2.Position;
import com.insignia.toyrobotv2.commands.Place;
import com.insignia.toyrobotv2.exception.GameException;
import com.insignia.toyrobotv2.serialize.NameSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component

public class Table {

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
    @JsonSerialize(using = NameSerializer.class)
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


    public boolean detectCollision(Position newPosition) {
        return robots.stream().anyMatch(e -> e.getPosition().equals(newPosition));
    }


    public boolean isOnTable(Position newPosition) {
        int x = newPosition.getX();
        int y = newPosition.getY();
        return x >= 0 && x < tableLength && y >= 0 && y < tableBreadth;
    }

    public void validateMove(Position newPosition) throws GameException {

        if (!isOnTable(newPosition))
            throw new GameException("Edge detected : Coordinates out of table dimension");

        if (detectCollision(newPosition))
            throw new GameException("Collision Detected");
    }

    public Robot getRobotById(int id) {
        // Get the Active Robot
        Optional<Robot> optionalToyRobot = getRobots()
                .stream()
                .filter(r -> (r.getId() == id))
                .findFirst();

        return optionalToyRobot.get();
    }


    @Override
    public String toString() {

        String res = "\n";


        for (Robot robot : robots) {
            res += robot.toString();
            if (robot.getId() == activeRobotId)
                res += " *";

            res += "\n";
        }
        return res;
    }
}
