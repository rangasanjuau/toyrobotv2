package com.insignia.toyrobotv2.commands;

import com.insignia.toyrobotv2.Position;
import com.insignia.toyrobotv2.exception.GameException;
import com.insignia.toyrobotv2.model.Robot;
import com.insignia.toyrobotv2.model.Table;
import com.insignia.toyrobotv2.response.ResponceDto;

public class Left extends Command {


    public Left(String[] commandTokens) {
        super(commandTokens);
    }


    public ResponceDto execute(Table table) throws GameException {

        Robot robot = table.getRobotById(table.getActiveRobotId());
        Position newPosition = robot.getPosition().getNextDirection("LEFT");
        robot.setPosition(newPosition);

        return ResponceDto.builder().robot(robot).build();
    }


}
