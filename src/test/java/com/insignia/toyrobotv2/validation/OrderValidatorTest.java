package com.insignia.toyrobotv2.validation;

import com.insignia.toyrobotv2.exception.GameException;
import com.insignia.toyrobotv2.model.Direction;
import com.insignia.toyrobotv2.model.Position;
import com.insignia.toyrobotv2.model.Robot;
import com.insignia.toyrobotv2.model.Table;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;

class OrderValidatorTest {



    @Test
    void testValidateOrderValidOrder() throws GameException {
        // Setup
        Table table = new Table();
        Direction direction = Direction.builder().robotDirection("NORTH").build();
        Position position = Position.builder().x(0).y(0).direction(direction).build();
        Robot robot = Robot.builder().id(1).position(position).build();
        table.getRobots().add(robot);

        OrderValidator orderValidator = new OrderValidator() {};

        // Exercise and Verify - No exception expected
        assertDoesNotThrow(() -> orderValidator.validateOrder(table));
    }

    @Test
    void testValidateOrderNoRobotPlaced() {
        // Setup
        Table table = new Table();
        OrderValidator orderValidator = new OrderValidator() {};

        // Exercise and Verify - Exception expected
        assertThrows(GameException.class, () -> orderValidator.validateOrder(table),
                "Please use the PLACE command first");
    }
}
