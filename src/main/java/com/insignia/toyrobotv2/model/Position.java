package com.insignia.toyrobotv2.model;

import com.insignia.toyrobotv2.util.ConfigUtil;
import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder

public class Position {

    int x;
    int y;
    String direction;

    public Position(int x, int y, String direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public Position getNextPosition() {

        int nx = x;
        int ny = y;

        // Get the position change for the given direction
        List<Integer> positionChange = ConfigUtil.getDirectionMap().get(direction);

        // Update the position
        nx += positionChange.get(0);
        ny += positionChange.get(1);

        return new Position(nx, ny, direction);
    }

    public Position getNextDirection(String to) {

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

        // Get the new direction and its position change
        String newDirection = directions[newDirectionIndex];

        return new Position(x, y, newDirection);
    }

}
