package com.challenge.demo.model;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class SateliteSplit implements Serializable {
    @ApiModelProperty(value = "Distance to ship", required = true, example = "100" , position = 2)
    float distance;
    @ApiModelProperty(value = "Array of messages", required = true, example = "['este','es','', '', ]" , position = 3)
    String [] message;

    public SateliteSplit(float distance, String[] message) {
        this.distance = distance;
        this.message = message;
    }

    public float getDistance() {
        return this.distance;
    }

    public String[] getMessage() {
        return this.message;
    }

    public Satelite build(String name) {
        return new Satelite(name, distance, message);
    }

}
