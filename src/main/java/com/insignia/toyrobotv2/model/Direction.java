package com.insignia.toyrobotv2.model;

import com.insignia.toyrobotv2.util.ConfigUtil;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Direction {

    private String direction;



    public Direction getNextDirection(String to) {

        // Get the current direction and its index
        String[] directions = ConfigUtil.getDirectionMap().keySet().toArray(new String[0]);
        int currentDirectionIndex = 0;

        for (int i = 0; i < directions.length; i++) {
            if (directions[i].equals(direction)) {
                currentDirectionIndex = i;
                break;
            }
        }

        // Calculate the new direction index
        int newDirectionIndex = (currentDirectionIndex + ConfigUtil.getRotation().get(to));
        newDirectionIndex = newDirectionIndex < 0 ? newDirectionIndex + directions.length : newDirectionIndex;
        newDirectionIndex = newDirectionIndex % directions.length;

        // Get the new direction
        return Direction.builder().direction(directions[newDirectionIndex]).build();
    }

}
