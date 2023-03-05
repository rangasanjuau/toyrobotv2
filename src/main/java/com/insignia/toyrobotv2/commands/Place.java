package com.insignia.toyrobotv2.commands;


import com.insignia.toyrobotv2.Position;
import com.insignia.toyrobotv2.exception.GameException;
import com.insignia.toyrobotv2.model.Robot;
import com.insignia.toyrobotv2.model.Table;
import com.insignia.toyrobotv2.response.ResponceDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Data
@NoArgsConstructor
@Component
public class Place extends Command{

    private final static Logger logger = LoggerFactory.getLogger(Place.class);

    public Place(String[] commandTokens) {
        super(commandTokens);
    }

    @Override
    public ResponceDto execute(Table table) throws GameException {

        Robot robot = null;

        int x = Integer.parseInt(commandTokens[1]);
        int y = Integer.parseInt(commandTokens[2]);
        Position position = Position.builder()
                .x(x)
                .y(y)
                .direction(commandTokens[3])
                .build();

        robot = Robot.builder()
                .id(getNextAvailableId(table))
                .position(position)
                .build();

        robot.setPosition(robot.getPosition());

        // If first robot then set ACTIVE
        if (table.getRobots().isEmpty()) {
            table.setActiveRobotId(robot.getId());
        }

        table.getRobots().add(robot);


        return ResponceDto.builder().robot(robot).table(table).build();
    }



    public int getNextAvailableId(Table table)
    {
        int maxId =  table.getRobots()
                .stream().mapToInt(Robot::getId).max().orElse(0);
        return maxId + 1;
    }

}
