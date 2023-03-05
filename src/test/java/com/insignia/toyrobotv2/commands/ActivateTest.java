package com.insignia.toyrobotv2.commands;

import com.insignia.toyrobotv2.exception.GameException;
import com.insignia.toyrobotv2.model.Direction;
import com.insignia.toyrobotv2.model.Position;
import com.insignia.toyrobotv2.model.Robot;
import com.insignia.toyrobotv2.model.Table;
import com.insignia.toyrobotv2.response.ResponceDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ActivateTest {

    private static Table table;

    @BeforeAll
    public static void setUp() {
        table = Table.getInstance(5,5);
        Direction direction = Direction.builder().direction("NORTH").build();
        Position position = Position.builder().x(0).y(0).direction(direction).build();
        Robot robot = Robot.builder().id(1).position(position).build();
        table.getRobots().add(robot);
    }


    @Test
    public void testExecute() throws GameException {

        String[] commandTokens = {"ACTIVATE", "1"};
        Activate activate = new Activate(commandTokens);

        // Act
        ResponceDto response = activate.execute(table);

        // Assert
        Assertions.assertEquals(1, table.getActiveRobotId());
        Assertions.assertEquals(1, response.getRobot().getId());
    }

    @Test
    public void testValidateArguments_NoOfArguments() throws GameException {
        // Arrange
        final String[] commandTokens = {"ACTIVATE"};
        final Activate activate = new Activate(commandTokens);

        // Act and Assert
        GameException exception = Assertions.assertThrows(GameException.class, () -> {
            activate.validateArguments(table, commandTokens);
        });
        Assertions.assertEquals("Not enough Arguments { Usage : ACTIVATE ID , Example : ACTIVATE 1 }", exception.getMessage());

    }


    @Test
    public void testValidateArguments() throws GameException {
        // Arrange
        final String[] commandTokens = {"ACTIVATE", "invalid"};
        final Activate activate = new Activate(commandTokens);

        // Act and Assert
        GameException exception = Assertions.assertThrows(GameException.class, () -> {
            activate.validateArguments(table, commandTokens);
        });
        Assertions.assertEquals("Invalid id, Integer Expected { Usage : ACTIVATE ID , Example : ACTIVATE 1 }", exception.getMessage());
    }
}
