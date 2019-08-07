package com.stackroute.muzixassignment.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.stackroute.muzixassignment.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzixassignment.exceptions.TrackNotFoundException;
import com.stackroute.muzixassignment.model.Track;
import com.stackroute.muzixassignment.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
@Service //marks the class as service
public class TrackServiceImpl implements TrackService {
    private TrackRepository trackRepository;

    @Autowired
    public TrackServiceImpl(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Override
    public Track saveTrack(Track track) throws TrackAlreadyExistsException {
        if (trackRepository.existsById(track.getId())) {
            throw new TrackAlreadyExistsException("track already exists");
        }
        Track savedTrack = trackRepository.save(track);
        if (savedTrack == null) {
            throw new TrackAlreadyExistsException("track not found");
        }

    return savedTrack;
    }


    @Override
    public List<Track> getAllTrack()
    {
        return (List<Track>) trackRepository.findAll();
    }

    @Override
    public Optional<Track> deleteTrack(int id) throws TrackNotFoundException {
        Optional<Track> track1 = trackRepository.findById(id);

        if (!track1.isPresent()) {
            throw new TrackNotFoundException("User Not Found");
        }

         trackRepository.deleteById(id);
        return track1;

    }

    @Override
    public List<Track> getTrackByName(String firstName) throws TrackNotFoundException {
        List<Track> track = trackRepository.getUserByName(firstName);
        if (track.isEmpty()) {
            throw new TrackNotFoundException("User not found");
        }

        return trackRepository.getUserByName(firstName);
    }

    @Override
    public Track updateTrack(int id,Track track) {
        Track updateTrack = trackRepository.save(track);
        return updateTrack;
    }

    public Track fetchData()
    {
        final String uri = "http://ws.audioscrobbler.com/2.0/?method=artist.gettoptracks&artist=cher&api_key=3cc8ac30b3db666f9d334cd898067c20&format=json";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);


        //Object Mapper to access the JSON from the response entity
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = null;
        Track track = new Track();

        //read the response body to get JSON object
        try {
            root = mapper.readTree(result.getBody());
            ArrayNode arrayNode = (ArrayNode) root.path("tracks").path("track");

            //iterate the JSON array
            for (int i = 0; i < arrayNode.size(); i++) {
                //get a new Track object and fill it with data using setters

                track.setName(arrayNode.get(i).path("name").asText());
                track.setComments(arrayNode.get(i).path("artist").path("name").asText());
                //save the track to database
                trackRepository.save(track);
            }



        } catch (IOException e) {
            e.printStackTrace();
        }
        return track;

    }
}

//
//    }
//@Override
//public List<Track> fetchData(String firstName) {
//    return trackRepository.fetchData(firstName);
//}

