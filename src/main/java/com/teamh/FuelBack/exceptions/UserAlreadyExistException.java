package com.teamh.FuelBack.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAlreadyExistException extends RuntimeException{
    private final Class type;
    private final String field;
    private final int status;

    public UserAlreadyExistException(String msg, Class type, String field){
        super(msg);
        this.field = field;
        this.type = type;
        this.status = 401;
    }
}
