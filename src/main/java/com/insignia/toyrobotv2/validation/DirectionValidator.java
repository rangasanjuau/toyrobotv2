package com.insignia.toyrobotv2.validation;

import com.insignia.toyrobotv2.exception.GameException;
import com.insignia.toyrobotv2.model.Table;

import java.util.Objects;

public interface DirectionValidator {

    // Default method, Can be Overridden if required
    default void validateDirection(Table table, String direction) throws GameException
    {
        // Check if direction is valid
        if (Objects.isNull(table.getDirectionMap().get(direction)))
            throw new GameException("Invalid Direction : Pernmitted values are " + table.getDirectionMap().keySet());

    }
}
