package com.insignia.toyrobotv2.util;

import com.insignia.toyrobotv2.commands.Command;
import com.insignia.toyrobotv2.exception.GameException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ToyUtil {

    private static final String COMMAND_PACKAGE = "com.insignia.toyrobotv2.commands";

    public static boolean isNAN(String num) {
        try {
            Integer.parseInt(num);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }


    public static Command getCommandInstance(String className, Object commandTokens) throws GameException {

        Command command;

        // Get the class object using Reflection
        Class<?> commandClass = null;
        try {
            commandClass = Class.forName(COMMAND_PACKAGE + "." + className);


            // Get the constructor of the class
            Constructor<?> constructor = commandClass.getConstructor(String[].class);

            // Create an instance of the class using the constructor
            command = (Command) constructor.newInstance(commandTokens);

        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            throw new GameException("Command Not Implemented");
        }
        return command;
    }
}
