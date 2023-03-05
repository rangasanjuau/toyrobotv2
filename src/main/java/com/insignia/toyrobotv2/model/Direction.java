package com.insignia.toyrobotv2.model;


import com.insignia.toyrobotv2.exception.GameException;
import com.insignia.toyrobotv2.util.ConfigUtil;

import java.util.Objects;

public class Direction {
    String direction;
    public static void validateDirection(String direction) throws GameException {
        // Check if direction is valid
        if (Objects.isNull(ConfigUtil.getDirectionMap().get(direction)))
            throw new GameException("Invalid Direction : Pernmitted values are " + ConfigUtil.getDirectionMap().keySet());
    }

}
