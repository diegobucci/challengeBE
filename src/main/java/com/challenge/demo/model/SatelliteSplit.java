package com.challenge.demo.model;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class SatelliteSplit implements Serializable {
    @ApiModelProperty(value = "Distance to ship", required = true, example = "100" , position = 2)
    private float distance;
    @ApiModelProperty(value = "Array of messages", required = true, example = "['este','es','', '', ]" , position = 3)
    private String [] message;

    public SatelliteSplit(float distance, String[] message) {
        this.distance = distance;
        this.message = message;
    }

    public float getDistance() {
        return this.distance;
    }

    public String[] getMessage() {
        return this.message;
    }

    public Satellite build(String name) {
        return new Satellite(name, distance, message);
    }

}
