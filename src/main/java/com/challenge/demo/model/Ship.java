package com.challenge.demo.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.lang.NonNull;

import java.awt.geom.Point2D;
import java.io.Serializable;

@ApiModel(value = "Ship data")
public class Ship implements Serializable {

    @ApiModelProperty(value = "Ship position", required = true, position = 1)
    private Point2D.Float position;
    @ApiModelProperty(value = "Help message", required = true, example = "este es un mensaje de auxilio", position = 2)
    private String message;

    public Ship(float x, float y, String message){
        this.position = new Point2D.Float(x, y);
        this.message = message;
    }

    public Point2D getPosition() {
        return this.position;
    }

    public String getMessage() {
        return this.message;
    }
}
