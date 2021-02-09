package com.challenge.demo.controller;

import com.challenge.demo.model.*;
import com.challenge.demo.service.APIService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class APIController {

    @Autowired
    private APIService apiService;

    @ApiOperation(value = "Send data and get the position with the help message", tags = "XWing")
    @PostMapping(value = "/api/v1/topsecret", consumes = "application/json", produces = "application/json")
    @ApiResponses(value = {
    @ApiResponse(code = 200, message = "OK", response = Ship.class),
    @ApiResponse(code = 404, message = "ERROR", response = String.class)})
    public ResponseEntity<Ship> postFleetData(@RequestBody List<Satellite> satellites) {
        return apiService.postFleetDataService(satellites);
    }

    @ApiOperation(value = "Send data from satelite", tags = "XWing")
    @PostMapping(value = "/api/v1/topsecret_split/{satellite_name}", consumes = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "CREATED", response = String.class),
            @ApiResponse(code = 404, message = "ERROR", response = String.class)})
    public ResponseEntity postSatelliteData(@RequestBody SatelliteSplit satelliteSplit, @PathVariable("satellite_name") String name){
        return apiService.postSatelliteDataService(satelliteSplit, name);
    }

    @ApiOperation(value = "Get ship position and help message", tags = "XWing")
    @GetMapping(value = "/api/v1/topsecret_split", produces = "application/json")
    public ResponseEntity<Ship> getShipData() {
        return apiService.getShipDataService();
    }
}
