package dao;

import dataSources.Alumni;

import java.util.List;

public interface AlumniDao {
    void add(Alumni alumni);

    List<Alumni> getAllAlumni();

    Alumni findById(int id);

    void updateAlumni(String name, Integer age, String lastJob, String gender, Integer trackId, String zipcode, String currentTrack, Boolean graduated, String currentJob, Integer daysToJob, int id);

    void removeAllAlumni();
}
