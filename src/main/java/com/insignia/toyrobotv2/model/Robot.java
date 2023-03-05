package com.insignia.toyrobotv2.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.insignia.toyrobotv2.Position;
import com.insignia.toyrobotv2.serialize.NameSerializer;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Robot {
    @JsonProperty("id")
    @JsonSerialize(using = NameSerializer.class)
    private int id;

    private Position position;

}
