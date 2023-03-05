package com.insignia.toyrobotv2.validation;

import com.insignia.toyrobotv2.exception.GameException;
import com.insignia.toyrobotv2.util.ConfigUtil;

import java.util.Objects;

public interface DirectionValidator {

    // Default method, Can be Overridden if required
    default void validateDirection(String direction) throws GameException
    {
        // Check if direction is valid
        if (Objects.isNull(ConfigUtil.getDirectionMap().get(direction)))
            throw new GameException("Invalid Direction : Pernmitted values are " + ConfigUtil.getDirectionMap().keySet());

    }
}
