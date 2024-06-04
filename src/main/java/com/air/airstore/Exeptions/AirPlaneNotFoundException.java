package com.air.airstore.Exeptions;

public class AirPlaneNotFoundException extends RuntimeException{
    public AirPlaneNotFoundException(String message) {
        super(message);
    }
}
