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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class LeftTest {

    @Mock
    private Table table;

    private Left left;

    private LinkedHashMap<String, List<Integer>> map;
    private LinkedHashMap<String, Integer> rotation;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        String[] commandTokens = {"MOVE"};
        left = spy(new Left(commandTokens));

        map = new LinkedHashMap<>();
        map.put("NORTH", new ArrayList<Integer>(Arrays.asList(0, 1)));
        map.put("EAST", new ArrayList<Integer>(Arrays.asList(-1, 0)));
        map.put("SOUTH", new ArrayList<Integer>(Arrays.asList(0, -1)));
        map.put("WEST", new ArrayList<Integer>(Arrays.asList(1, 0)));


        rotation = new LinkedHashMap<>();
        rotation.put("LEFT", -1);
        rotation.put("RIGHT", 1);
    }

    @Test
    @DisplayName("Test execute method with valid input")
    void testExecuteValidInput() throws GameException {
        // Mock the table's getRobotById method to return a robot with the given ID
        Direction currentDirection = Direction.builder().robotDirection("NORTH").build();
        Direction nextDirection = Direction.builder().robotDirection("WEST").build();
        Position position = Position.builder().x(0).y(0).direction(currentDirection).build();
        Robot robot = Robot.builder().id(1).position(position).build();
        when(table.getRobotById(anyInt())).thenReturn(robot);
        when(table.getDirectionMap()).thenReturn(map);
        when(table.getRotation()).thenReturn(rotation);

        // Mock the validateOrder and validatePosition methods to do nothing
        doNothing().when(left).validateOrder(any());

        ResponceDto response = left.execute(table);
        assertNotNull(response.getRobot());
        assertEquals(1, response.getRobot().getId());
        assertEquals(0, response.getRobot().getPosition().getX());
        assertEquals(0, response.getRobot().getPosition().getY());
        assertEquals(nextDirection, response.getRobot().getPosition().getDirection());
    }
}