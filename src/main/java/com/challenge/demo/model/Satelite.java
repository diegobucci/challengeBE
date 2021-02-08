package com.challenge.demo.model;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.lang.NonNull;
import java.awt.geom.Point2D;
import java.io.Serializable;

public class Satelite implements Serializable {
    @ApiModelProperty(value = "Satelite name", required = true, example = "kenobi", position = 1)
    String name;
    @ApiModelProperty(value = "Satelite distance to ship", required = true, example = "105.2", position = 2)
    float distance;
    @ApiModelProperty(value = "Message from the ship", required = true, position = 3)
    String[] message;

    public Satelite(String name, float distance, String[] message) {
        this.name = name;
        this.distance = distance;
        this.message = message;
    }

    public String getName() {
        return this.name;
    }

    public String[] getMessage(){
        return this.message;
    }

    public float getDistance() {
        return this.distance;
    }

}
