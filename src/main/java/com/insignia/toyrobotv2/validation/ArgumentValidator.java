package com.insignia.toyrobotv2.validation;

import com.insignia.toyrobotv2.exception.GameException;
import com.insignia.toyrobotv2.model.Table;
import com.insignia.toyrobotv2.util.ToyUtil;

public interface ArgumentValidator {
    default void validateArguments(Table table, String[] commandTokens) throws GameException
    {

        if (commandTokens.length < 4)
            throw new GameException("Not enough Arguments { Usage : PLACE X Y DIRECTION , Example : PLACE 2 3 NORTH }");


        // Check if (X,Y) coordinates are valid
        if (ToyUtil.isNAN(commandTokens[1]) || ToyUtil.isNAN(commandTokens[2]))
            throw new GameException("Invalid Coordinates, Integer Expected { Usage : PLACE X Y DIRECTION , Example : PLACE 2 3 NORTH }");
    }

}
