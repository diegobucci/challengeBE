package com.challenge.demo.model;

import io.swagger.annotations.ApiModelProperty;

public abstract class Message {
    @ApiModelProperty(value = "Satelite distance to ship", required = true, example = "105.2", position = 2)
    float distance;
    @ApiModelProperty(value = "Message from the ship", required = true, position = 3)
    String[] message;

    public Message(float distance, String[] message) {
        this.distance = distance;
        this.message = message;
    }

    public Message() {

    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public String[] getMessage() {
        return message;
    }

    public void setMessage(String[] message) {
        this.message = message;
    }
}
