package com.insignia.toyrobotv2.commands;

import com.insignia.toyrobotv2.exception.GameException;
import com.insignia.toyrobotv2.model.Direction;
import com.insignia.toyrobotv2.model.Position;
import com.insignia.toyrobotv2.model.Robot;
import com.insignia.toyrobotv2.model.Table;
import com.insignia.toyrobotv2.response.ResponceDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MoveTest {

    @Mock
    private Table table;

    private Move move;

    @Mock
    private Position position;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        String[] commandTokens = {"MOVE"};
        move = spy(new Move(commandTokens));
    }

    @Test
    @DisplayName("Test execute method with valid input")
    void testExecuteValidInput() throws GameException {
        // Mock the table's getRobotById method to return a robot with the given ID
        Direction direction = Direction.builder().direction("NORTH").build();
        Robot robot = Robot.builder().id(1).position(position).build();
        when(table.getRobotById(anyInt())).thenReturn(robot);

        // Mock the robot's getNextPosition method to return a valid position
        when(position.getNextPosition(any())).thenReturn(Position.builder().x(0).y(1).direction(direction).build());

        // Mock the validateOrder and validatePosition methods to do nothing
        doNothing().when(move).validateOrder(any());
        doNothing().when(move).validatePosition(any(),any());

        ResponceDto response = move.execute(table);
        assertNotNull(response.getRobot());
        assertEquals(1, response.getRobot().getId());
        assertEquals(0, response.getRobot().getPosition().getX());
        assertEquals(1, response.getRobot().getPosition().getY());
        assertEquals(direction, response.getRobot().getPosition().getDirection());
    }


    @Test
    @DisplayName("Test execute method with invalid position")
    void testExecuteInvalidPosition() throws GameException {
        // Mock the table's getRobotById method to return a robot with the given ID
        Direction direction = Direction.builder().direction("NORTH").build();
        Robot robot = Robot.builder().id(1).position(position).build();
        when(table.getRobotById(anyInt())).thenReturn(robot);

        // Mock the robot's getNextPosition method to return an invalid position
        when(position.getNextPosition(any())).thenReturn(Position.builder().x(-1).y(0).direction(direction).build());

        // Mock the validateOrder method to do nothing
        doNothing().when(move).validateOrder(any());

        // Call execute method and verify that a GameException is thrown with the correct message
        GameException exception = assertThrows(GameException.class, () -> move.execute(table));
        assertTrue(exception.getMessage().contains("Edge detected"));
    }
}