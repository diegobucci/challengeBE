package com.challenge.demo.controller;

import com.challenge.demo.model.*;
import com.challenge.demo.service.APIService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class APIController {

    @Autowired
    private APIService apiService;

    @ApiOperation(value = "Send data and get the position with the help message", tags = "XWing")
    @PostMapping(value = "/api/v1/topsecret", consumes = "application/json", produces = "application/json")
    @ApiResponses(value = @ApiResponse(code = 404, message = "ERROR", response = String.class))
    public ResponseEntity<Ship> postFleetData(@RequestBody Fleet fleet) {
        return apiService.postFleetDataService(fleet);
    }

    @ApiOperation(value = "Send data from satelite", tags = "XWing")
    @PostMapping(value = "/api/v1/topsecret_split/{satellite_name}", consumes = "application/json")
    @ResponseStatus(code = HttpStatus.CREATED)
    @ApiResponses(value = @ApiResponse(code = 404, message = "ERROR", response = String.class))
    public HttpStatus postSatelliteData(@RequestBody SatelliteSplit satelliteSplit, @PathVariable("satellite_name") String name){
        return apiService.postSatelliteDataService(satelliteSplit, name);
    }

    @ApiOperation(value = "Get ship position and help message", tags = "XWing")
    @GetMapping(value = "/api/v1/topsecret_split", produces = "application/json")
    @ApiResponses(value = @ApiResponse(code = 404, message = "ERROR", response = String.class))
    public ResponseEntity<Ship> getShipData() {
        return apiService.getShipDataService();
    }
}
