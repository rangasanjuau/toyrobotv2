package com.insignia.toyrobotv2.validation;

import com.insignia.toyrobotv2.commands.Command;
import com.insignia.toyrobotv2.exception.GameException;
import com.insignia.toyrobotv2.util.ConfigUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

public interface CommandValidator {


    default void validateCommand(String command) throws GameException {
        // Check if command is valid
        if (Objects.isNull(ConfigUtil.getCommands().get(command)))
        {
            throw new GameException("Invalid Command. Permiited commands are " + ConfigUtil.getCommands().keySet());
        }
    }

}
