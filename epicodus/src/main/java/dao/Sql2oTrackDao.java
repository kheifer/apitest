package dao;

import models.Track;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

public class Sql2oTrackDao implements TrackDao {
    private final Sql2o sql2o;

    public Sql2oTrackDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public void add(Track newTrack) {
        String query = "INSERT into tracks (focus, description, duration) VALUES (:focus, :description, :duration)";
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(query)
                    .bind(newTrack)
                    .executeUpdate()
                    .getKey();
            newTrack.setTrackId(id);
        }catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void addTrackToCohort(int cohortId, int trackId) {
        String query = "INSERT INTO cohorts_tracks (cohortId, trackId) VALUES (:cohortId, :trackId)";
        try (Connection con = sql2o.open()) {
            con.createQuery(query)
                    .addParameter("cohortId", cohortId)
                    .addParameter("trackId", trackId)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Track> getAll() {
        String query = "SELECT * FROM tracks";
        try(Connection con = sql2o.open()){
            return con.createQuery(query)
                    .executeAndFetch(Track.class);
        }
    }

    @Override
    public List<Track> getAllTracksByCohort(int cohortId) {
        String query = "SELECT trackId FROM campuses_tracks WHERE cohortId = :cohortId";
        List<Track> locations = new ArrayList<>();
        try(Connection con = sql2o.open()){
                List<Integer> trackIds = con.createQuery(query)
                        .addParameter("cohortId", cohortId)
                        .executeAndFetch(Integer.class);
                 for (Integer track: trackIds) {
                     locations.add(
                      con.createQuery("SELECT * FROM tracks WHERE trackId = :trackId")
                            .addParameter("trackId", track)
                            .executeAndFetchFirst(Track.class));
                }
                return locations;
            }
        }

    @Override
    public Track findById(int trackId) {
        String query = "SELECT * FROM cohorts WHERE trackId = :trackId";
        try(Connection con = sql2o.open()){
            return con.createQuery(query)
                    .addParameter("trackId", trackId)
                    .executeAndFetchFirst(Track.class);
        }
    }

    @Override
    public void deleteTrack(int trackId) {
        String query = "DELETE FROM cohorts WHERE trackId = :trackId";
        try(Connection con = sql2o.open()){
             con.createQuery(query)
                    .addParameter("trackId", trackId)
                    .executeUpdate();
        }catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
}
