package com.insignia.toyrobotv2.commands;

import com.insignia.toyrobotv2.exception.GameException;
import com.insignia.toyrobotv2.model.Table;
import com.insignia.toyrobotv2.response.ResponceDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Command {
    protected String[] commandTokens;

    public abstract ResponceDto execute(Table table) throws GameException;
}
