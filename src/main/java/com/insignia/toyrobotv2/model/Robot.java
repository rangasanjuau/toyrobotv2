package com.insignia.toyrobotv2.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Robot {
    @JsonProperty("id")
    private int id;

    private Position position;

}
