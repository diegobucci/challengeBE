package com.challenge.demo.model;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class SateliteSplit extends Message implements Serializable {
    @ApiModelProperty(value = "Satelite name", required = true, example = "kenobi", position = 1)
    Message message;


    public SateliteSplit(Message message) {
        super();
        this.message = message;
    }

    public float getDistance() {
        return this.distance;
    }
}
