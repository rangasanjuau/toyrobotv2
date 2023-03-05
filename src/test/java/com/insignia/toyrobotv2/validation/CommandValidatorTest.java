package com.insignia.toyrobotv2.validation;

import com.insignia.toyrobotv2.commands.Right;
import com.insignia.toyrobotv2.exception.GameException;
import com.insignia.toyrobotv2.model.Table;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

class CommandValidatorTest {

    @Mock
    private Table table;

    private LinkedHashMap<String, Integer> commands;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        commands = new LinkedHashMap<>();
        commands.put("PLACE", 0);
        commands.put("MOVE", 1);
        commands.put("LEFT", 2);
        commands.put("RIGHT", 3);
        commands.put("REPORT", 4);
        commands.put("ACTIVATE", 5);
    }


    @Test
    void testValidateCommandValidCommand() throws GameException {
        // Setup
        String command = "MOVE";
        CommandValidator commandValidator = new CommandValidator() {};

        when(table.getCommands()).thenReturn(commands);

        // Exercise and Verify - No exception expected
        assertDoesNotThrow(() -> commandValidator.validateCommand(table, command));
    }

    @Test
    void testValidateCommandInvalidCommand() {
        // Setup
        String command = "JUMP";
        CommandValidator commandValidator = new CommandValidator() {};

        // Exercise and Verify - Exception expected
        assertThrows(GameException.class, () -> commandValidator.validateCommand(table, command),
                "Invalid Command. Permiited commands are " + table.getCommands().keySet());
    }
}
