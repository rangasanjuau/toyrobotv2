package com.insignia.toyrobotv2.controller;

import com.insignia.toyrobotv2.Toyrobotv2Application;
import com.insignia.toyrobotv2.commands.Command;
import com.insignia.toyrobotv2.exception.GameException;
import com.insignia.toyrobotv2.model.Table;
import com.insignia.toyrobotv2.response.ResponceDto;
import com.insignia.toyrobotv2.util.ToyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class GameController {

    private static final Logger logger = LoggerFactory.getLogger(GameController.class);

    @Autowired
    private Table table;

    public void start()
    {
        // Get the USER INPUT
        Scanner scanner = new Scanner(System.in);
        String cmd = "";
        while (!cmd.equals("QUIT")) {
            System.out.print(" ( Type QUIT to exit ) Enter your command: ");
            if (scanner.hasNext()) {

                // Wait for input
                cmd = scanner.nextLine();
                String[] commandTokens = cmd.split(" ");

                try {
                    // Check if a command was entered
                    if (commandTokens.length == 0)
                        throw new GameException("Missing Command");

                    // Check if command is valid
                    table.validateCommand(table, commandTokens[0]);

                    // Get the appropriate command class
                    String className = commandTokens[0].toLowerCase().substring(0, 1).toUpperCase() + commandTokens[0].substring(1).toLowerCase();
                    Command command = ToyUtil.getCommandInstance(className, commandTokens);

                    // Execute the corrosponding commands execute
                    command.execute(table);

                } catch (GameException ex) {
                    logger.info("EXCEPTION : " + ex.getMessage());
                }

            }

        }

    }

}
