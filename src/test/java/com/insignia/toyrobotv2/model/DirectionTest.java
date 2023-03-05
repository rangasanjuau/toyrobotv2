package com.insignia.toyrobotv2.model;


import com.insignia.toyrobotv2.commands.Right;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;


public class DirectionTest {

    @Mock
    private Table table;


    private LinkedHashMap<String, List<Integer>> map;
    private LinkedHashMap<String, Integer> rotation;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        String[] commandTokens = {"MOVE"};

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
    public void testGetNextDirectionNorthRight() {
        // Setup
        Direction direction = Direction.builder().direction("NORTH").build();

        when(table.getDirectionMap()).thenReturn(map);
        when(table.getRotation()).thenReturn(rotation);

        // Exercise
        Direction nextDirection = direction.getNextDirection(table, "RIGHT");

        // Verify
        assertEquals("EAST", nextDirection.getDirection());
    }

    @Test
    public void testGetNextDirectionSouthLeft() {
        // Setup
        Direction direction = Direction.builder().direction("SOUTH").build();

        when(table.getDirectionMap()).thenReturn(map);
        when(table.getRotation()).thenReturn(rotation);

        // Exercise
        Direction nextDirection = direction.getNextDirection(table, "LEFT");

        // Verify
        assertEquals("EAST", nextDirection.getDirection());
    }

}
