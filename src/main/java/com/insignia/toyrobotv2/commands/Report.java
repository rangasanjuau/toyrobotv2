package com.insignia.toyrobotv2.commands;


import com.insignia.toyrobotv2.Toyrobotv2Application;
import com.insignia.toyrobotv2.exception.GameException;
import com.insignia.toyrobotv2.model.Robot;
import com.insignia.toyrobotv2.model.Table;
import com.insignia.toyrobotv2.response.ResponceDto;
import com.insignia.toyrobotv2.validation.OrderValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Report extends Command implements OrderValidator {

    private final static Logger logger = LoggerFactory.getLogger(Report.class);

    public Report(String[] commandTokens) {
        super(commandTokens);
    }


    public ResponceDto execute(Table table) throws GameException {
        validateOrder(table);

        String res = "";

        // Print the table status

        res += "\nTABLE (" + table.getTableLength()+ " X " + table.getTableBreadth() + ") \n";
        res += "------------------------\n";

        for (Robot robot : table.getRobots()) {
            res += "Robot " + robot.getId() + ": " + robot.getPosition().getX() + " " + robot.getPosition().getY()
                    + " " + robot.getPosition().getDirection().getDirection() +  " ";
            if (robot.getId() == table.getActiveRobotId())
                res += " *";

            res+="\n";
        }

        System.out.println(res + "\n");

        return ResponceDto.builder().table(table).build();
    }

}
