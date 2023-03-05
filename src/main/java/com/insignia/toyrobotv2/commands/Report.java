package com.insignia.toyrobotv2.commands;


import com.insignia.toyrobotv2.exception.GameException;
import com.insignia.toyrobotv2.model.Table;
import com.insignia.toyrobotv2.response.ResponceDto;
import com.insignia.toyrobotv2.validation.OrderValidator;

public class Report extends Command implements OrderValidator {

    public Report(String[] commandTokens) {
        super(commandTokens);
    }


    public ResponceDto execute(Table table) throws GameException {
        validateOrder(table);
        return ResponceDto.builder().table(table).build();
    }

}
