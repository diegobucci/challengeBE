package com.challenge.demo.util;

import com.challenge.demo.exception.NegativeDistanceException;
import com.challenge.demo.exception.ShipNotFoundException;
import com.challenge.demo.model.Fleet;
import com.challenge.demo.model.Ship;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class Communication {
    private final static float ERROR_RANGE = 0.01f;

    private static Point2D.Float kenobi = new Point2D.Float(-500,-200);
    private static Point2D.Float skywalker = new Point2D.Float(100,-100);
    private static Point2D.Float sato = new Point2D.Float(500,100);

    private static Logger logger = LoggerFactory.getLogger(Communication.class);

    public static Ship getHelpMessage(Fleet myFleet) throws Exception {
        float[] resp;
        String helpMessage;

        try {
            //Los valores que hacen cumplir la condición son => DisKenobi = 583.1f, DistSkywalker = 500f, DistSato = 728f
            resp = getLocation(myFleet.getAllDistances());
            helpMessage = Message.getMessage(myFleet.getAllMessages());
        } catch (Exception e) {
            logger.info("Can't generate message and position");
            throw new ShipNotFoundException();
        }

        return new Ship(resp[0], resp[1], helpMessage);
    }

    private static void validateDistances(float[] distances, List<Double> ratios) throws Exception {
        ///convert to double
        for(float distance : distances) {
            if(distance < 0)
                throw new NegativeDistanceException();

            ratios.add((double)distance);
        }
        //Manejo de error
        if(!(ratios.isEmpty() | ratios.size() != 3))
            throw new ShipNotFoundException();
    }

    private static boolean validateSolution(float[] shipPosition, Point2D.Float satelite, double range){
        float calculatedRange = (float) (Math.pow( shipPosition[0] - satelite.getX() , 2) + Math.pow( shipPosition[1] - satelite.getY() , 2));
        return (Math.abs(calculatedRange - range)/range < ERROR_RANGE);
    }

    public static float[] getLocation(float ... distances) throws Exception {
        float[] shipPosition = new float[2];
        List<Double> ratios = new ArrayList<>();

        validateDistances(distances, ratios);

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
            throw new ShipNotFoundException();
        }

        return shipPosition;
    }

}
