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

import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doNothing;

class PlaceTest {


    @Mock
    private Table table;


    private Place place;

    private LinkedHashMap<String, List<Integer>> map;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        String[] commandTokens = {"PLACE", "0", "0", "NORTH"};
        place = spy(new Place(commandTokens));
    }

    @Test
    @DisplayName("Test execute method with valid input")
    void testExecuteValidInput() throws GameException {

        Direction direction = Direction.builder().robotDirection("NORTH").build();
        Position position = Position.builder().x(0).y(0).direction(direction).build();
        Robot robot = Robot.builder().id(1).position(position).build();
        when(table.getRobotById(anyInt())).thenReturn(robot);

        doNothing().when(place).validateDirection(any(),any());
        doNothing().when(place).validatePosition(any(),any());


        ResponceDto response = place.execute(table);

        assertNotNull(response.getRobot());
        assertEquals(1, response.getRobot().getId());
        assertEquals(0, response.getRobot().getPosition().getX());
        assertEquals(0, response.getRobot().getPosition().getY());
    }


}
