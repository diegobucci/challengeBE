package com.challenge.demo.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

public class Satelite implements Serializable {
    @ApiModelProperty(value = "Satelite name", required = true, example = "kenobi", position = 1)
    String name;
    @ApiModelProperty(value = "Distance to ship", required = true, example = "100" , position = 2)
    float distance;
    @ApiModelProperty(value = "Array of messages", required = true, example = "['este','es','', '', ]" , position = 3)
    String [] message;

    public Satelite(String name, float distance, String[] message) {
        this.name = name;
        this.distance = distance;
        this.message = message;
    }

    public String getName() {
        return this.name;
    }

    public float getDistance() {
        return this.distance;
    }

    public String[] getMessage() {
        return this.message;
    }

}
