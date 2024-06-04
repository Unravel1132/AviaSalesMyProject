package com.air.airstore.Exceptions;

public class AirPlaneNotFoundException extends RuntimeException{
    public AirPlaneNotFoundException(String message) {
        super(message);
    }
}
