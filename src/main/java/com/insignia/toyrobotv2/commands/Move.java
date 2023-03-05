package com.insignia.toyrobotv2.commands;


import com.insignia.toyrobotv2.model.Position;
import com.insignia.toyrobotv2.exception.GameException;
import com.insignia.toyrobotv2.model.Robot;
import com.insignia.toyrobotv2.model.Table;
import com.insignia.toyrobotv2.response.ResponceDto;
import com.insignia.toyrobotv2.validation.OrderValidator;
import com.insignia.toyrobotv2.validation.PositionValidator;

public class Move extends Command implements OrderValidator, PositionValidator {

    public Move(String[] commandTokens) {
        super(commandTokens);
    }


    public ResponceDto execute(Table table) throws GameException {
        validateOrder(table);

        Robot robot = table.getRobotById(table.getActiveRobotId());
        Position newPosition = robot.getPosition().getNextPosition(table);

        validatePosition(table, newPosition);
        robot.setPosition(newPosition);

        return ResponceDto.builder().robot(robot).build();
    }

}
