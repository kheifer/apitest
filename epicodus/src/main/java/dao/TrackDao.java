package dao;

import models.Track;

import java.util.List;

public interface TrackDao {
    //Create
    void add(Track newTrack);
    void addTrackToCohort(int cohortId, int trackId);

    //Read
    List<Track> getAll();

    List<Track> getAllTracksByCohort(int cohortId);

    Track findById(int trackId);

    //DELETE
    void deleteTrack(int trackId);

}
