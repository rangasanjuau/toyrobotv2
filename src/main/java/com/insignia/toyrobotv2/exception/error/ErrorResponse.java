package com.insignia.toyrobotv2.exception.error;


import lombok.Data;

@Data
public class ErrorResponse {
    private int errorCode;
    private String message;
    private String field;

}
