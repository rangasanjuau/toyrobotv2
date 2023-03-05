package com.insignia.toyrobotv2.commands;


import com.insignia.toyrobotv2.exception.GameException;
import com.insignia.toyrobotv2.model.Robot;
import com.insignia.toyrobotv2.model.Table;
import com.insignia.toyrobotv2.response.ResponceDto;
import com.insignia.toyrobotv2.validation.OrderValidator;

public class Report extends Command implements OrderValidator {

    public Report(String[] commandTokens) {
        super(commandTokens);
    }


    public ResponceDto execute(Table table) throws GameException {
        validateOrder(table);

        String res = "";

        // Print the table status

        res += "\nTABLE (" + table.getTableLength() + " X " + table.getTableBreadth() + ") \n";
        res += "------------------------\n";

        for (Robot robot : table.getRobots()) {
            res += "Robot " + robot.getId() + ": " + robot.getPosition().getX() + " " + robot.getPosition().getY()
                    + " " + robot.getPosition().getDirection().getRobotDirection() + " ";
            if (robot.getId() == table.getActiveRobotId())
                res += " *";

            res += "\n";
        }

        System.out.println(res + "\n");

        return ResponceDto.builder().table(table).build();
    }

}
