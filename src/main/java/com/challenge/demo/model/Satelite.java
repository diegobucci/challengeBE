package com.challenge.demo.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

public class Satelite extends Message implements Serializable {
    @ApiModelProperty(value = "Satelite name", required = true, example = "kenobi", position = 1)
    String name;
    Message message;


    public Satelite(String name, Message message) {
        super();
        this.name = name;
        this.message = message;
    }

    public String getName() {
        return this.name;
    }

    public float getDistance() {
        return this.distance;
    }

    public void setName(String name) {
        this.name = name;
    }
}
