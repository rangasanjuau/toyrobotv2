package com.insignia.toyrobotv2.commands;


import com.insignia.toyrobotv2.Position;
import com.insignia.toyrobotv2.exception.GameException;
import com.insignia.toyrobotv2.model.Direction;
import com.insignia.toyrobotv2.model.Robot;
import com.insignia.toyrobotv2.model.Table;
import com.insignia.toyrobotv2.response.ResponceDto;
import com.insignia.toyrobotv2.validation.ArgumentValidator;
import com.insignia.toyrobotv2.validation.CommandValidator;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Data
@NoArgsConstructor
@Component
public class Place extends Command implements ArgumentValidator {

    private final static Logger logger = LoggerFactory.getLogger(Place.class);

    public Place(String[] commandTokens) {
        super(commandTokens);
    }

    @Override
    public ResponceDto execute(Table table) throws GameException {

        Robot robot = null;
        validateArguments(table);

        int x = Integer.parseInt(commandTokens[1]);
        int y = Integer.parseInt(commandTokens[2]);

        robot = Robot.builder().id(getNextAvailableId(table)).position(Position.builder().x(x).y(y).direction("NORTH").build()).build();
        robot.setPosition(robot.getPosition());

        // If first robot then set ACTIVE
        if (table.getRobots().isEmpty()) {
            table.setActiveRobotId(robot.getId());
        }

        table.getRobots().add(robot);


        return ResponceDto.builder().robot(robot).table(table).build();
    }


    @Override
    public void validateArguments(Table table) throws GameException {


        if (commandTokens.length != 4)
            throw new GameException("Not enough Arguments { Usage : PLACE X Y DIRECTION , Example : PLACE 2 3 NORTH }");


        // Check if (X,Y) coordinates are valid
        if (CommandValidator.isNAN(commandTokens[1]) || CommandValidator.isNAN(commandTokens[2]))
            throw new GameException("Invalid Coordinates, Integer Expected { Usage : PLACE X Y DIRECTION , Example : PLACE 2 3 NORTH }");


        // Check if direction is valid
        Direction.validateDirection(commandTokens[3]);

        int x = Integer.parseInt(commandTokens[1]);
        int y = Integer.parseInt(commandTokens[2]);
        Position position = Position.builder().x(x).y(y).direction(commandTokens[3]).build();

        // Validate if the position is valid on table
        table.validateMove(position);


    }

    public int getNextAvailableId(Table table)
    {
        int maxId =  table.getRobots()
                .stream().mapToInt(Robot::getId).max().orElse(0);
        return maxId + 1;
    }

}
