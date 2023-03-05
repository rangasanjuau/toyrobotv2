package com.insignia.toyrobotv2.validation;

import com.insignia.toyrobotv2.model.Position;
import com.insignia.toyrobotv2.exception.GameException;
import com.insignia.toyrobotv2.model.Table;

public interface PositionValidator {

    // Default method, Can be Overridden if required
    default void validatePosition(Table table, Position newPosition) throws GameException
    {
        if (!table.isOnTable(newPosition))
            throw new GameException("Edge detected : Coordinates out of table dimension");

        if (table.isNotOccupied(newPosition))
            throw new GameException("Collision Detected");

    }
}
