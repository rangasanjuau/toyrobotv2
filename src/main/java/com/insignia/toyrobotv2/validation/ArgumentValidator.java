package com.insignia.toyrobotv2.validation;

import com.insignia.toyrobotv2.exception.GameException;
import com.insignia.toyrobotv2.model.Table;

public interface ArgumentValidator {
    void validateArguments(Table table) throws GameException;

}
