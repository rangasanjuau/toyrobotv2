package com.insignia.toyrobotv2.validation;

import com.insignia.toyrobotv2.exception.GameException;
import com.insignia.toyrobotv2.model.Table;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public interface CommandValidator {

    default void validateCommand(Table table, String command) throws GameException {
        // Check if command is valid
        if (Objects.isNull(table.getCommands().get(command)))
        {
            throw new GameException("Invalid Command. Permiited commands are " + table.getCommands().keySet());
        }
    }

}
