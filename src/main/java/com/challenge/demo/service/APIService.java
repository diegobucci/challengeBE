package com.challenge.demo.service;

import com.challenge.demo.controller.APIController;
import com.challenge.demo.model.Satellite;
import com.challenge.demo.model.SatelliteSplit;
import com.challenge.demo.util.Communication;
import com.challenge.demo.model.Fleet;
import com.challenge.demo.model.Ship;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class APIService {
    private Fleet myFleet;
    private Logger logger = LoggerFactory.getLogger(APIService.class);

    public ResponseEntity<Ship> postFleetDataService(List<Satellite> satellites) {
        Ship response;

        myFleet = new Fleet(satellites);
        System.out.println("ACA!");
        try {
            response = Communication.getHelpMessage(myFleet);
            System.out.println("Response "+response);
        } catch (Exception e) {
            logger.info("Response not found");
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<Ship> getShipDataService() {
        Ship response;

        try {
            if(myFleet.getSatelliteQuantity() < 3)
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            else {
                response = Communication.getHelpMessage(myFleet);
            }
        } catch (Exception e) {
            logger.info("Response not found");
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity postSatelliteDataService(SatelliteSplit satelliteSplit, String name) {
        Satellite satellite = satelliteSplit.build(name);

        try {
            myFleet.addSatellite(satellite);
        } catch (Exception e) {
            logger.info("Error, can't create satelite");
            return new ResponseEntity(HttpStatus.CONFLICT);
        }

        return new ResponseEntity(HttpStatus.CREATED);
    }
}
