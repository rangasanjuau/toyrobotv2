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
    Direction direction;

    public Position(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public Position getNextPosition() {

        int nx = x;
        int ny = y;

        // Get the position change for the given direction
        List<Integer> positionChange = ConfigUtil.getDirectionMap().get(direction.getDirection());

        // Update the position
        nx += positionChange.get(0);
        ny += positionChange.get(1);

        return new Position(nx, ny, direction);
    }

}
