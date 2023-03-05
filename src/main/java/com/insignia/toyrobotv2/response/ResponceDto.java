package com.insignia.toyrobotv2.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.insignia.toyrobotv2.model.Robot;
import com.insignia.toyrobotv2.model.Table;
import lombok.Builder;
import lombok.Data;

import java.util.Objects;

@Builder
@Data
public class ResponceDto {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Robot robot;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Table table;

    @Override
    public String toString() {

        String contents="";

        if(!Objects.isNull(robot))
            contents =  robot.toString();

        if(!Objects.isNull(table))
            contents =  table.toString();

        return contents;

    }
}
