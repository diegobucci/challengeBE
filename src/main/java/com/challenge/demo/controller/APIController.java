package com.challenge.demo.controller;

import com.challenge.demo.model.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class APIController {

    private static Fleet myfleet;
    private static Communication myCommunication;

    @ApiOperation(value = "Get the position and the help message", tags = "XWing")
    @PostMapping(value = "/api/v1/topsecret", consumes = "application/json", produces = "application/json")
    @ApiResponses(value = {
    @ApiResponse(code = 200, message = "OK", response = Ship.class),
    @ApiResponse(code = 404, message = "ERROR", response = String.class)})
    public ResponseEntity<Ship> postFleetData(@RequestBody Communication communication) {
        Ship response;
        myCommunication = communication;
        myfleet = new Fleet(communication.getSatelites());
        try {
            response = myCommunication.getHelpMessage(myfleet);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(value = "Send data from satelite", tags = "XWing")
    @PostMapping(value = "/api/v1/topsecret_split/{satelite_name}", consumes = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = String.class),
            @ApiResponse(code = 404, message = "ERROR", response = String.class)})
    public ResponseEntity postSateliteData(@RequestBody SateliteSplit sateliteSplit, @PathVariable("satelite_name") String name){

        Satelite satelite = sateliteSplit.build(name);

        if(myCommunication == null) {
            myCommunication = new Communication();
            myfleet = new Fleet(satelite);
        }
        else
            myfleet.updateSatelite(satelite);

        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "Get ship position and help message", tags = "XWing")
    @GetMapping(value = "/api/v1/topsecret_split", produces = "application/json")
    public ResponseEntity<Ship> getShipData() {
        Ship response;

        try {
            if(myfleet.getSateliteQuantity() < 3)
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            else {
                response = myCommunication.getHelpMessage(myfleet);
            }
        } catch (Exception e) {
          return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
