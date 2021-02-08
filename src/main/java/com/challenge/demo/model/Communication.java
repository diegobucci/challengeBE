package com.challenge.demo.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;

import java.awt.geom.Point2D;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

@ApiModel(value = "satelites")
public class Communication implements Serializable {
    @ApiModelProperty(required = true)
    private Satelite[] satelites;
    private final static float ERROR_RANGE = 0.01f;
    private final static String ERROR = "404";

    private Point2D.Float kenobi = new Point2D.Float(-500,-200);
    private Point2D.Float skywalker = new Point2D.Float(100,-100);
    private Point2D.Float sato = new Point2D.Float(500,100);

    public Communication(){}
    public Communication(Satelite... satelites) {
        this.satelites =  satelites;
    }

    public Satelite[] getSatelites() {
        return satelites;
    }

    public float[] getLocation(float ... distances) throws Exception {
        float[] shipPosition = new float[2];
        List<Double> ratios = new ArrayList<>();

        if(!validateDistances(distances, ratios)){
            throw new Exception(ERROR);
        }

        double rangeKenobi = Math.pow(ratios.get(0), 2);
        double rangeSkywalker = Math.pow(ratios.get(1), 2);
        double rangeSato = Math.pow(ratios.get(2), 2);

        double deltaY1, deltaY2, numerator1, numerator2;
        double slope1, slope2, interceptY1, interceptY2;

        deltaY1 = skywalker.getY() - kenobi.getY();
        deltaY2 = sato.getY() - kenobi.getY();

        numerator1 = Math.pow(skywalker.getX(), 2) + Math.pow(skywalker.getY(), 2) - Math.pow(kenobi.getX(), 2) - Math.pow(kenobi.getY(), 2);
        numerator2 = Math.pow(sato.getX(), 2)      + Math.pow(sato.getY(), 2)      - Math.pow(kenobi.getX(), 2) - Math.pow(kenobi.getY(), 2);

        slope1 = ( skywalker.getX() - kenobi.getX() ) / deltaY1;
        slope2 = ( sato.getX()      - kenobi.getX() ) / deltaY2;

        interceptY1 = ( rangeKenobi - rangeSkywalker + numerator1 ) / deltaY1;
        interceptY2 = ( rangeKenobi - rangeSato      + numerator2 ) / deltaY2;

        shipPosition[0] = (float) (( interceptY1 - interceptY2 ) / ( 2*(slope1 - slope2) ));
        shipPosition[1] = (float) (( interceptY1/2 ) - ( slope1*shipPosition[0] ));

        if(!validateSolution(shipPosition, kenobi, rangeKenobi) && !validateSolution(shipPosition, skywalker, rangeSkywalker) && !validateSolution(shipPosition, sato, rangeSato)){
            throw new Exception(ERROR);
        }

        return shipPosition;

    }

    public String getMessage( String[] ...allMessages) throws Exception {
        List<List<String>> messages = new ArrayList<>(validateMessages(allMessages));

        //busco el mensaje con mas elementos
        List<String> finalMessage = new ArrayList<>(searchBestMessage(messages));
        //lo elimino de la lista
        messages.remove(finalMessage);

        //alineo el mensaje final
        alignMessage(messages, finalMessage);

        completeMessage(messages, finalMessage);

        removeShift(finalMessage);

        if(finalMessage.contains("")) {
            throw new Exception(ERROR);
        }

        return String.join(" ", finalMessage);
    }

    public Ship getHelpMessage(Fleet myfleet) {
        float resp [] = new float[0];
        String helpMessage = new String();

        try {
            //Los valores que hacen cumplir la condición son => DisKenobi = 583.1f, DistSkywalker = 500f, DistSato = 728f
            resp = getLocation(myfleet.getAllDistances());
            helpMessage = getMessage(myfleet.getAllMessages());

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return new Ship(resp[0], resp[1], helpMessage);
    }

    private boolean validateDistances(float[] distances, List<Double> ratios) {
        ///convert to double
        for(float ratio : distances) {
            ratios.add((double)ratio);
        }
        //Manejo de error
        if(ratios.isEmpty() | ratios.size() != 3)
            return false;

        return true;
    }

    private static boolean validateSolution(float[] shipPosition, Point2D.Float satelite, double range){
        float calculatedRange = (float) (Math.pow( shipPosition[0] - satelite.getX() , 2) + Math.pow( shipPosition[1] - satelite.getY() , 2));
        return (Math.abs(calculatedRange - range)/range < ERROR_RANGE);

    }

    private List<List<String>> validateMessages(String[] ... allMessages) throws Exception {
        List<List<String>> messages = new ArrayList<>();

        //delete null message
        for(String[] message : allMessages) {
            if(message != null) {
                List<String> aux = Arrays.asList(message);
                messages.add(aux);
            }
        }

        //Manejo de error
        if(messages.isEmpty())
            throw new Exception(ERROR);

        List<List<String>> auxMessages = new ArrayList<>(messages);

        //delete empty message
        for(List<String> message : messages) {
            int elements = message.size() - Collections.frequency(message, "");
            if(elements == 0) {
                auxMessages.remove(message);
            }
        }

        return auxMessages;
    }

    private List<String> searchBestMessage(List<List<String>> messages) {
        int maxElements = 0;
        List<String> finalMessage = new ArrayList<>();

        for(List<String> message : messages) {
            int elements = message.size() - Collections.frequency(message, "");
            if(elements > maxElements) {
                maxElements = elements;
                finalMessage = message;
            }
        }

        return finalMessage;
    }

    private List<String> deleteShift(List<String> finalMessage) {
        List<String> aux = finalMessage;

        while(finalMessage.get(0).equals("")){
            aux.remove(0);
        }
        return aux;
    }

    private void alignMessage(List<List<String>> messages, List<String> finalMessage) {
        for(List<String> message : messages) {
            for(String word : message) {
                if (word != "" && finalMessage.contains(word)) {
                    while (finalMessage.indexOf(word) - message.indexOf(word) > 0) {
                        if (finalMessage.get(0).equals("")) {
                            finalMessage.remove(0);
                        }
                    }
                }
            }
        }
    }

    private void removeShift(List<String> finalMessage) {
        if(finalMessage.get(0).equals("")) {
            finalMessage = deleteShift(finalMessage);
        } else if(finalMessage.get(finalMessage.size()-1).equals("")) {
            Collections.reverse(finalMessage);
            finalMessage = deleteShift(finalMessage);
            Collections.reverse(finalMessage);
        }
    }

    private void completeMessage(List<List<String>> messages, List<String> finalMessage) throws Exception {
        List<List<String>> auxMessages = new ArrayList<>(messages);

        for(int i = 0; auxMessages.size() != 0 && i < messages.size() ; i++ ) {
            for (List<String> message : messages) {
                AtomicInteger shift = new AtomicInteger();
                AtomicBoolean hasShift = new AtomicBoolean(false);

                if (finalMessage.containsAll(message)) {
                    auxMessages.remove(message);
                } else {
                    getShift(finalMessage, message, shift, hasShift);
                    if (hasShift.get()) {
                        fillMessage(finalMessage, message, shift);
                        auxMessages.remove(message);
                    }
                    hasShift.set(false);
                }
            }
        }
        if(auxMessages.size()>0){
            throw new Exception(ERROR);
        }
    }

    private void fillMessage(List<String> finalMessage, List<String> message, AtomicInteger shift) {
        message.forEach((word) -> {
            if (!word.equals("") && !finalMessage.contains(word)) {
                finalMessage.set(message.indexOf(word) -shift.get(), word);
            }
        });
    }

    private void getShift(List<String> finalMessage, List<String> message, AtomicInteger shift, AtomicBoolean hasShift) {
        message.forEach((word) -> {
            if (!word.equals("") && finalMessage.contains(word)) {
                shift.set(message.indexOf(word) - finalMessage.indexOf(word));
                hasShift.set(true);
            }
        });
    }
}
