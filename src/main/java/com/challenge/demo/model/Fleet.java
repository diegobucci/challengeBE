package com.challenge.demo.model;

import java.awt.geom.Point2D;
import java.lang.Math;

public class Fleet {
    private Point2D.Float kenobi;
    private Point2D.Float skywalker;
    private Point2D.Float sato;  //lo declaro point para no crear una clase de datos.
    private final static float ERROR_RANGE = 0.01f;

    public Fleet(){
        this.kenobi = new Point2D.Float(-500,-200);
        this.skywalker = new Point2D.Float(100,-100);
        this.sato = new Point2D.Float(500, 100);
    }

    public float[] GetLocation(float distKenobi, float distSkywalker, float distSato ) throws Exception {
        float[] shipPosition = new float[2];

        double rangeKenobi = Math.pow(distKenobi, 2);
        double rangeSkywalker = Math.pow(distSkywalker, 2);
        double rangeSato = Math.pow(distSato, 2);

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
            System.out.println("Algo salio mal");
            throw new Exception("Valores invalidos");
        }

        return shipPosition;

    }

    private static boolean validateSolution(float[] shipPosition, Point2D.Float satelite, double range){
        float calculatedRange = (float) (Math.pow( shipPosition[0] - satelite.getX() , 2) + Math.pow( shipPosition[1] - satelite.getY() , 2));
        return (Math.abs(calculatedRange - range)/range < ERROR_RANGE);

    }


}
