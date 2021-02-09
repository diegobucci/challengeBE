package com.challenge.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ApiModel(value = "Satellites")
public class Fleet implements Serializable {
    @JsonProperty
    @ApiModelProperty(required = true, position = 1)
    private List<Satellite> satellites = new ArrayList<>();

    public Fleet() {

    }
    public Fleet(List<Satellite> allSatellites) {
        this.satellites = allSatellites;
    }

    @JsonIgnore
    public int getSatelliteQuantity(){
        return satellites.size();
    }

    public void addSatellite(Satellite satellite) throws Exception {
        if(!satellites.isEmpty()) {
            for (Satellite element : satellites) {
                if (element.getName().equals(satellite.getName())) {
                    throw new Exception("This satellite already exist");
                }
            }
        }
        satellites.add(satellite);
    }

    @JsonIgnore
    public float[] getAllDistances() {
        float[] distances = new float[3];
        int i = 0;
        for (Satellite satellite : satellites) {
            if(satellite.getName().equals("kenobi"))
                distances[0] = satellite.getDistance();
            else if(satellite.getName().equals("skywalker"))
                distances[1] = satellite.getDistance();
            else
                distances[2] = satellite.getDistance();
        }
        return distances;
    }

    @JsonIgnore
    public String[][] getAllMessages() {
        List<String []> messages = new ArrayList<>();

        for (Satellite satellite : satellites) {
            messages.add(satellite.getMessage());
        }

        return messages.toArray(new String[3][]);
    }

}
