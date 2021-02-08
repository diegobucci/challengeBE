package com.challenge.demo.model;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.PrimitiveIterator;

public class Fleet {
    List<Satelite> satelites = new ArrayList<>();

    public Fleet(Satelite ... allSatelites) {
        for(int i= 0 ; i< allSatelites.length; i++) {
            satelites.add(allSatelites[i]);
        }
    }

    public int getSateliteQuantity(){
        return satelites.size();
    }

    public void updateSatelite(Satelite satelite) {
        boolean updated = false;
        for (Satelite element : satelites) {
            System.out.println("element: "+element.getName());
            if (element.getName().equals(satelite.getName())) {
                satelites.set(satelites.indexOf(element), satelite);
                System.out.println("Aca satelite a updatear: "+satelite.getName());
                updated = true;
            }
        }
        if(!updated){
            satelites.add(satelite);
            System.out.println("se agrego: "+satelite.getName());
        }
    }

    public float[] getAllDistances() {
        float[] distances = new float[3];
        int i = 0;
        for (Satelite satelite : satelites) {
            if(satelite.getName().equals("kenobi"))
                distances[0] = satelite.getDistance();
            else if(satelite.getName().equals("skywalker"))
                distances[1] = satelite.getDistance();
            else
                distances[2] = satelite.getDistance();
        }
        return distances;
    }

    public String[][] getAllMessages() {
        List<String []> messages = new ArrayList<>();

        for (Satelite satelite : satelites) {
            messages.add(satelite.getMessage());
        }

        return messages.toArray(new String[3][]);
    }

}
