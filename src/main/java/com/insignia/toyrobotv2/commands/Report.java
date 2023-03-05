package com.insignia.toyrobotv2.commands;


import com.insignia.toyrobotv2.exception.GameException;
import com.insignia.toyrobotv2.model.Table;
import com.insignia.toyrobotv2.response.ResponceDto;

public class Report extends Command {


    public Report(String[] commandTokens) {
        super(commandTokens);
    }


    public ResponceDto execute(Table table) throws GameException {
        return ResponceDto.builder().table(table).build();
    }

}
