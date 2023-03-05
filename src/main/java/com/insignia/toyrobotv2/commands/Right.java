package com.insignia.toyrobotv2.commands;

import com.insignia.toyrobotv2.model.Position;
import com.insignia.toyrobotv2.exception.GameException;
import com.insignia.toyrobotv2.model.Robot;
import com.insignia.toyrobotv2.model.Table;
import com.insignia.toyrobotv2.response.ResponceDto;
import com.insignia.toyrobotv2.validation.OrderValidator;

public class Right extends Command implements OrderValidator {

    public Right(String[] commandTokens) {
        super(commandTokens);
    }

    public ResponceDto execute(Table table) throws GameException {

        validateOrder(table);

        Robot robot = table.getRobotById(table.getActiveRobotId());
        Position newPosition = robot.getPosition().getNextDirection("RIGHT");
        robot.setPosition(newPosition);

        return ResponceDto.builder().robot(robot).build();
    }

}
