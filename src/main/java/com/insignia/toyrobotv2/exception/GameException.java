package com.insignia.toyrobotv2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class GameException extends Exception{

    public GameException(String message) {

        super(message);

    }
}