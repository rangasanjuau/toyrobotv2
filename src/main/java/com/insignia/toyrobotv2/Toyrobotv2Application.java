package com.insignia.toyrobotv2;

import com.insignia.toyrobotv2.commands.Command;
import com.insignia.toyrobotv2.exception.GameException;
import com.insignia.toyrobotv2.model.Table;
import com.insignia.toyrobotv2.response.ResponceDto;
import com.insignia.toyrobotv2.util.ConfigUtil;
import com.insignia.toyrobotv2.util.ToyUtil;
import com.insignia.toyrobotv2.validation.CommandValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;


@SpringBootApplication
public class Toyrobotv2Application implements CommandLineRunner {


	private final static Logger logger = LoggerFactory.getLogger(Toyrobotv2Application.class);

	@Value("#{${robot.rotation}}")
	private LinkedHashMap<String, Integer> rotation;

	@Value("#{${robot.commands}}")
	private LinkedHashMap<String, Integer> commands;

	@Value("#{${robot.directions}}")
	private LinkedHashMap<String, List<Integer>> directions;

	@Value("${table.length}")
	private int length;

	@Value("${table.breadth}")
	private int breadth;

	private static Table table;


	public static void main(String[] args) {

		SpringApplication.run(Toyrobotv2Application.class, args);

		// Create Table
		table = Table.getInstance(ConfigUtil.getTableLength(), ConfigUtil.getTableBreadth());

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
					if (commandTokens.length < 0)
						throw new GameException("Missing Command");

					// Check if command is valid
					table.validateCommand(commandTokens[0]);

					// Get the appropriate command class
					String className = commandTokens[0].toLowerCase().substring(0, 1).toUpperCase() + commandTokens[0].substring(1).toLowerCase();
					Command command = ToyUtil.getCommandInstance(className, commandTokens);

					// Execute the corrosponding commands execute
					ResponceDto responce = command.execute(table);

					//logger.info(responce.toString());
				} catch (GameException ex) {
					logger.info("EXCEPTION : " + ex.getMessage());
				}

			}

		}


	}

	// Populate Configurable items from properties file
	@Override
	public void run(String... args) throws Exception {

		ConfigUtil.setRotation(rotation);
		ConfigUtil.setCommands(commands);
		ConfigUtil.setDirectionMap(directions);
		ConfigUtil.setTableLength(length);
		ConfigUtil.setTableBreadth(breadth);
	}

}
