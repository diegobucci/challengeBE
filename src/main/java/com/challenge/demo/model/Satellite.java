package com.challenge.demo.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

public class Satellite implements Serializable {
    @ApiModelProperty(value = "Satellite name", required = true, example = "kenobi", position = 1)
    private String name;
    @ApiModelProperty(value = "Distance to ship", required = true, example = "100" , position = 2)
    private float distance;
    @ApiModelProperty(value = "Array of messages", required = true, example = "['este','es','', '', ]" , position = 3)
    private String [] message;

    Satellite(String name, float distance, String[] message) {
        this.name = name;
        this.distance = distance;
        this.message = message;
    }

    String getName() {
        return this.name;
    }

    float getDistance() {
        return this.distance;
    }

    String[] getMessage() {
        return this.message;
    }

}
