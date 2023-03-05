package com.insignia.toyrobotv2.validation;

import com.insignia.toyrobotv2.exception.GameException;
import com.insignia.toyrobotv2.model.Table;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ArgumentValidatorTest {

    @Test
    void testValidateArgumentsNotEnoughArguments() {
        // Setup
        Table table = new Table();
        String[] commandTokens = new String[]{"PLACE", "2", "3"};
        ArgumentValidator argumentValidator = new ArgumentValidator() {};

        // Exercise and Verify - Exception expected
        assertThrows(GameException.class, () -> argumentValidator.validateArguments(table, commandTokens),
                "Not enough Arguments { Usage : PLACE X Y DIRECTION , Example : PLACE 2 3 NORTH }");
    }

    @Test
    void testValidateArgumentsInvalidCoordinates() {
        // Setup
        Table table = new Table();
        String[] commandTokens = new String[]{"PLACE", "2", "A", "NORTH"};
        ArgumentValidator argumentValidator = new ArgumentValidator() {};

        // Exercise and Verify - Exception expected
        assertThrows(GameException.class, () -> argumentValidator.validateArguments(table, commandTokens),
                "Invalid Coordinates, Integer Expected { Usage : PLACE X Y DIRECTION , Example : PLACE 2 3 NORTH }");
    }

    @Test
    void testValidateArgumentsValidArguments() throws GameException {
        // Setup
        Table table = new Table();
        String[] commandTokens = new String[]{"PLACE", "2", "3", "NORTH"};
        ArgumentValidator argumentValidator = new ArgumentValidator() {};

        // Exercise and Verify - No exception expected
        assertDoesNotThrow(() -> argumentValidator.validateArguments(table, commandTokens));
    }
}
