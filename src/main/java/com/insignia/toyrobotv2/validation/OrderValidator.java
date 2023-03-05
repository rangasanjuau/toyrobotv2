package com.insignia.toyrobotv2.validation;

import com.insignia.toyrobotv2.exception.GameException;
import com.insignia.toyrobotv2.model.Table;

public interface OrderValidator {

    // Default method, Can be Overridden if required
    default void validateOrder(Table table) throws GameException
    {
        if (table.getRobots().isEmpty())
            throw new GameException("Please use the PLACE command first");
    }
}
