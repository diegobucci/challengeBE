package com.challenge.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

public class Satellite implements Serializable {
    @JsonProperty
    @ApiModelProperty(value = "Satellite name", required = true, example = "kenobi", position = 1)
    private String name;
    @JsonProperty
    @ApiModelProperty(value = "Distance to ship", required = true, example = "100" , position = 2)
    private float distance;
    @JsonProperty
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
