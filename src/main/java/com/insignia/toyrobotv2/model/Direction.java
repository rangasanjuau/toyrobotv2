package com.insignia.toyrobotv2.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Direction {

    private String direction;



    public Direction getNextDirection(Table table, String to) {

        // Get the current direction and its index
        String[] directions = table.getDirectionMap().keySet().toArray(new String[0]);
        int currentDirectionIndex = 0;

        for (int i = 0; i < directions.length; i++) {
            if (directions[i].equals(direction)) {
                currentDirectionIndex = i;
                break;
            }
        }

        // Calculate the new direction index
        int newDirectionIndex = (currentDirectionIndex + table.getRotation().get(to));
        newDirectionIndex = newDirectionIndex < 0 ? newDirectionIndex + directions.length : newDirectionIndex;
        newDirectionIndex = newDirectionIndex % directions.length;

        // Get the new direction
        return Direction.builder().direction(directions[newDirectionIndex]).build();
    }

}
