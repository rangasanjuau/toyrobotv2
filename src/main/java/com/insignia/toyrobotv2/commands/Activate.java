package com.insignia.toyrobotv2.commands;

import com.insignia.toyrobotv2.exception.GameException;
import com.insignia.toyrobotv2.model.Direction;
import com.insignia.toyrobotv2.model.Robot;
import com.insignia.toyrobotv2.model.Table;
import com.insignia.toyrobotv2.response.ResponceDto;
import com.insignia.toyrobotv2.util.ToyUtil;
import com.insignia.toyrobotv2.validation.ArgumentValidator;
import com.insignia.toyrobotv2.validation.OrderValidator;

import java.util.Objects;
import java.util.Optional;

public class Activate extends Command implements ArgumentValidator, OrderValidator {


    public Activate(String[] commandTokens) {
        super(commandTokens);
    }


    public ResponceDto execute(Table table) throws GameException {

        validateOrder(table);
        validateArguments(table,commandTokens);

        int id = Integer.parseInt(commandTokens[1]);
        Robot robot = table.getRobotById(id);

        table.setActiveRobotId(id);

        return ResponceDto.builder().robot(robot).build();
    }

    @Override
    public void validateArguments(Table table, String[] commandTokens) throws GameException
    {

        if (commandTokens.length < 2)
            throw new GameException("Not enough Arguments { Usage : ACTIVATE ID , Example : ACTIVATE 1 }");

        // Check if id is a valid integer
        if (ToyUtil.isNAN(commandTokens[1]))
            throw new GameException("Invalid id, Integer Expected { Usage : ACTIVATE ID , Example : ACTIVATE 1 }");

    }



}
