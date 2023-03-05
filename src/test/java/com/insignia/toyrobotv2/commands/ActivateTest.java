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
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class ActivateTest {

    @Mock
    private Table table;

    private Activate activate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        String[] commandTokens = {"ACTIVATE", "1"};
        activate = spy(new Activate(commandTokens));
    }

    @Test
    @DisplayName("Test execute method with valid input")
    void testExecuteValidInput() throws GameException {
        // Mock the table's getRobotById method to return a robot with the given ID
        Direction direction = Direction.builder().robotDirection("NORTH").build();
        Position position = Position.builder().x(0).y(0).direction(direction).build();
        Robot robot = Robot.builder().id(1).position(position).build();
        when(table.getRobotById(1)).thenReturn(robot);

        // Mock the validateOrder method to do nothing
        doNothing().when(activate).validateOrder(table);

        ResponceDto response = activate.execute(table);
        assertNotNull(response.getRobot());
        assertEquals(1, response.getRobot().getId());
    }

    @Test
    @DisplayName("Test execute method with invalid id")
    void testExecuteInvalidId() throws GameException {
        String[] commandTokens = {"ACTIVATE", "invalid"};
        activate = spy(new Activate(commandTokens));

        // Mock the validateOrder method to do nothing
        doNothing().when(activate).validateOrder(table);

        // Call execute method and verify that a GameException is thrown with the correct message
        GameException exception = assertThrows(GameException.class, () -> activate.execute(table));
        assertTrue(exception.getMessage().contains("Invalid id"));
    }

    @Test
    @DisplayName("Test validateArguments method with not enough arguments")
    void testValidateArgumentsNotEnoughArgs() throws GameException {
        String[] commandTokens = {"ACTIVATE"};
        activate = spy(new Activate(commandTokens));

        // Mock the validateOrder method to do nothing
        doNothing().when(activate).validateOrder(table);

        // Call validateArguments method and verify that a GameException is thrown with the correct message
        GameException exception = assertThrows(GameException.class, () -> activate.validateArguments(table, commandTokens));
        assertTrue(exception.getMessage().contains("Not enough Arguments"));
    }

    @Test
    @DisplayName("Test validateArguments method with invalid id")
    void testValidateArgumentsInvalidId() throws GameException {
        String[] commandTokens = {"ACTIVATE", "invalid"};
        activate = spy(new Activate(commandTokens));

        // Mock the validateOrder method to do nothing
        doNothing().when(activate).validateOrder(table);

        // Call validateArguments method and verify that a GameException is thrown with the correct message
        GameException exception = assertThrows(GameException.class, () -> activate.validateArguments(table, commandTokens));
        assertTrue(exception.getMessage().contains("Invalid id"));
    }

    @Test
    @DisplayName("Test validateArguments method with NaN id")
    void testValidateArgumentsNaN() throws GameException {
        String[] commandTokens = {"ACTIVATE", "NaN"};
        activate = spy(new Activate(commandTokens));

        // Mock the validateOrder method to do nothing
        doNothing().when(activate).validateOrder(table);

        // Call validateArguments method and verify that a GameException is thrown with the correct message
        GameException exception = assertThrows(GameException.class, () -> activate.validateArguments(table, commandTokens));
        assertTrue(exception.getMessage().contains("Invalid id"));
    }

}