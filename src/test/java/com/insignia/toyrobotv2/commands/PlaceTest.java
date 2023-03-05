package com.insignia.toyrobotv2.commands;

import com.insignia.toyrobotv2.exception.GameException;
import com.insignia.toyrobotv2.model.Direction;
import com.insignia.toyrobotv2.model.Position;
import com.insignia.toyrobotv2.model.Robot;
import com.insignia.toyrobotv2.model.Table;
import com.insignia.toyrobotv2.response.ResponceDto;
import com.insignia.toyrobotv2.util.ConfigUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.*;
public class PlaceTest {

    private static Table table;

    @BeforeAll
    public static void setUp() {
        table = Table.getInstance(5,5);
        Direction direction = Direction.builder().direction("NORTH").build();
        Position position = Position.builder().x(0).y(0).direction(direction).build();


        List list = new ArrayList<>(Arrays. asList(0,1));
        LinkedHashMap map = new LinkedHashMap<String, List<Integer>>();
        map.put("NORTH", list);
        ConfigUtil.setDirectionMap(map);
    }

    @Test
    public void testExecute() throws GameException {


        String[] commandTokens = new String[] { "PLACE", "1", "2", "NORTH" };
        Place place = new Place(commandTokens);

        ResponceDto response = place.execute(table);


        Assertions.assertEquals(table, response.getTable(), "Table should match");
        Assertions.assertNotNull(response.getRobot(), "Robot should not be null");
        Assertions.assertEquals(1, response.getRobot().getId(), "Robot id should match");
        Assertions.assertEquals(1, table.getRobots().size(), "Robots list should contain 1 robot");
        Assertions.assertEquals(response.getRobot(), table.getRobotById(1), "Robot should be added to the table");
        Assertions.assertEquals(response.getRobot().getId(), table.getActiveRobotId(), "Robot should be active");
        Assertions.assertEquals(1, response.getRobot().getPosition().getX(), "X position should match");
        Assertions.assertEquals(2, response.getRobot().getPosition().getY(), "Y position should match");
        Assertions.assertEquals("NORTH", response.getRobot().getPosition().getDirection().getDirection(),"Direction should match");
    }

    @Test
    public void testExecute_InvalidDirection() {

        String[] commandTokens = new String[] { "PLACE", "1", "2", "NORTHEAST" };
        Place place = new Place(commandTokens);

        // Act and Assert
        Assertions.assertThrows(GameException.class, () -> place.execute(table), "Should throw GameException for invalid direction");
    }

    @Test
    public void testExecute_PositionNotOnTable() {

        String[] commandTokens = new String[] { "PLACE", "10", "10", "NORTH" };
        Place place = new Place(commandTokens);


        Assertions.assertThrows(GameException.class, () -> place.execute(table), "Should throw GameException for position not on table");
    }

    @Test
    public void testGetNextAvailableId() {

        Robot robot1 = Robot.builder().id(1).build();
        Robot robot2 = Robot.builder().id(2).build();
        table.getRobots().add(robot1);
        table.getRobots().add(robot2);
        Place place = new Place(new String[0]);


        int nextId = place.getNextAvailableId(table);


        Assertions.assertEquals(3, nextId, "Next available ID should be 3");
    }
}
