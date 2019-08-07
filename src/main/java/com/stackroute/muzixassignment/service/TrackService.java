package com.stackroute.muzixassignment.service;

import com.stackroute.muzixassignment.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzixassignment.exceptions.TrackNotFoundException;
import com.stackroute.muzixassignment.model.Track;


import java.util.List;
import java.util.Optional;

public interface TrackService {
    public Track saveTrack(Track track) throws TrackAlreadyExistsException;
    public List<Track> getAllTrack();
    public Optional<Track> deleteTrack(int id) throws TrackNotFoundException;
    public List<Track> getTrackByName(String firstName) throws TrackNotFoundException;
    public Track updateTrack(int id,Track track);
    public Track fetchData();
}
