package com.insignia.toyrobotv2.commands;

import com.insignia.toyrobotv2.model.Direction;
import com.insignia.toyrobotv2.model.Position;
import com.insignia.toyrobotv2.exception.GameException;
import com.insignia.toyrobotv2.model.Robot;
import com.insignia.toyrobotv2.model.Table;
import com.insignia.toyrobotv2.response.ResponceDto;
import com.insignia.toyrobotv2.validation.OrderValidator;

import java.util.Optional;

public class Left extends Command implements OrderValidator {


    public Left(String[] commandTokens) {
        super(commandTokens);
    }


    public ResponceDto execute(Table table) throws GameException {

        validateOrder(table);

        Robot robot = table.getRobotById(table.getActiveRobotId());
        Direction currentDirection = robot.getPosition().getDirection();

        robot.getPosition().setDirection(currentDirection.getNextDirection(table,"LEFT"));

        return ResponceDto.builder().robot(robot).build();
    }


}
