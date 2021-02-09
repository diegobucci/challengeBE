package com.challenge.demo.service;

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

@Service
public class APIService {
    private Fleet myFleet;
    private Logger logger = LoggerFactory.getLogger(APIService.class);

    public ResponseEntity<Ship> postFleetDataService(Fleet fleet) {
        Ship response;

        myFleet = fleet;
        try {
            response = Communication.getHelpMessage(myFleet);
        } catch (Exception e) {
            logger.info("Response not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<Ship> getShipDataService() {
        Ship response;

        try {
            if(myFleet.getSatelliteQuantity() < 3)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            else {
                response = Communication.getHelpMessage(myFleet);
            }
        } catch (Exception e) {
            logger.info("Response not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(response);
    }

    public HttpStatus postSatelliteDataService(SatelliteSplit satelliteSplit, String name) {
        Satellite satellite = satelliteSplit.build(name);
        try {
            if(myFleet == null) myFleet = new Fleet();
            myFleet.addSatellite(satellite);
        } catch (Exception e) {
            logger.info("Error, can't create satellite");
            return HttpStatus.CONFLICT;
        }

        return HttpStatus.CREATED;
    }
}
