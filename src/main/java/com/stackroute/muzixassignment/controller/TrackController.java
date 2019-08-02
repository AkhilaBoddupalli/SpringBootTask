package com.stackroute.muzixassignment.controller;


import com.stackroute.muzixassignment.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzixassignment.exceptions.TrackNotFoundException;
import com.stackroute.muzixassignment.model.Track;

import com.stackroute.muzixassignment.service.TrackService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@ControllerAdvice(basePackages="com.stackroute.muzixassignment")
public class TrackController {

    TrackService trackService;


    public TrackController(TrackService trackService)
    {
        this.trackService=trackService;
    }

    @PostMapping("track")
    @ExceptionHandler(TrackAlreadyExistsException.class)
    public ResponseEntity<?> saveTrack(@RequestBody Track track)
    {
        ResponseEntity responseEntity;
        try
        {
            trackService.saveTrack(track);
            responseEntity=new ResponseEntity<String>("successfully created",HttpStatus.CREATED);
        }
        catch(TrackAlreadyExistsException ex)
        {
            responseEntity=new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @GetMapping("track")
    public ResponseEntity<?> getAllTrack(){
        trackService.fetchData();
        return new ResponseEntity<List<Track>>(trackService.getAllTrack(),HttpStatus.OK);
    }
    @DeleteMapping("/track/{id}")
    public ResponseEntity<?> deleteTrack(@PathVariable int id){
        ResponseEntity responseEntity;
        try
        {
            trackService.deleteTrack(id);
            responseEntity=new ResponseEntity("successfully deleted",HttpStatus.OK);
        }
        catch(Exception ex)
        {
            responseEntity=new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }


    @PutMapping("/track/{id}")
    public ResponseEntity<?> updateTrack(@PathVariable int id,@RequestBody Track track)
    {
        ResponseEntity responseEntity;
        try
        {
            trackService.updateTrack(id,track);
            responseEntity=new ResponseEntity("successfully updated",HttpStatus.OK);
        }
        catch(Exception ex)
        {
            responseEntity=new ResponseEntity(ex.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
    @GetMapping("track/{firstName}")
    @ExceptionHandler(TrackNotFoundException.class)
    public ResponseEntity<?> getTrackByName(@PathVariable String firstName) {

        ResponseEntity responseEntity;

        try {
            responseEntity = new ResponseEntity<List<Track>>(trackService.getTrackByName(firstName), HttpStatus.CREATED);


        } catch (TrackNotFoundException e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);

        }
        return responseEntity;

    }


}
