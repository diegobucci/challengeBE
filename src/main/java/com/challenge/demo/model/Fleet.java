package com.challenge.demo.model;

import io.swagger.annotations.ApiModel;

import java.util.ArrayList;
import java.util.List;

@ApiModel(value = "Satellites")
public class Fleet {
    private List<Satellite> satellites;

    public Fleet(List<Satellite> allSatellites) {
        satellites = allSatellites;
    }

    public int getSatelliteQuantity(){
        return satellites.size();
    }

    public void addSatellite(Satellite satellite) throws Exception {
        for (Satellite element : satellites) {
            if (element.getName().equals(satellite.getName())) {
                throw new Exception("This satellite already exist");
            }
        }
        satellites.add(satellite);
    }

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

    public String[][] getAllMessages() {
        List<String []> messages = new ArrayList<>();

        for (Satellite satellite : satellites) {
            messages.add(satellite.getMessage());
        }

        return messages.toArray(new String[3][]);
    }

}
