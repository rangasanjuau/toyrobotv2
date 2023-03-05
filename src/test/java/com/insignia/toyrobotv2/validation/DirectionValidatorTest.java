package com.insignia.toyrobotv2.validation;

import com.insignia.toyrobotv2.exception.GameException;
import com.insignia.toyrobotv2.model.Table;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class DirectionValidatorTest {


    @Mock
    private Table table;

    private LinkedHashMap<String, List<Integer>> map;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        map = new LinkedHashMap<>();
        map.put("NORTH", new ArrayList<Integer>(Arrays.asList(0, 1)));
        map.put("EAST", new ArrayList<Integer>(Arrays.asList(-1, 0)));
        map.put("SOUTH", new ArrayList<Integer>(Arrays.asList(0, -1)));
        map.put("WEST", new ArrayList<Integer>(Arrays.asList(1, 0)));

    }


    @Test
    public void testValidateDirectionValidDirection() throws GameException {
        // Setup
        String direction = "NORTH";
        DirectionValidator directionValidator = new DirectionValidator() {};

        when(table.getDirectionMap()).thenReturn(map);

        // Exercise and Verify - No exception expected
        assertDoesNotThrow(() -> directionValidator.validateDirection(table, direction));
    }

    @Test
    public void testValidateDirectionInvalidDirection() {
        // Setup
        String direction = "NORTHWEST";
        DirectionValidator directionValidator = new DirectionValidator() {};

        when(table.getDirectionMap()).thenReturn(map);

        // Exercise and Verify - Exception expected
        assertThrows(GameException.class, () -> directionValidator.validateDirection(table, direction),
                "Invalid Direction : Pernmitted values are " + table.getDirectionMap().keySet());
    }
}
