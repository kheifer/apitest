package dao;

import com.sun.org.apache.xpath.internal.operations.Bool;
import models.Alumni;

import java.util.List;

public interface AlumniDao {
    void add(Alumni alumni);

    List<Alumni> getAllAlumni(Boolean graduated);

    Alumni findById(int id);

    void updateAlumni(String name, Integer age, String lastJob, String gender, String zipcode, String currentTrack, Boolean graduated, String currentJob, Integer daysToJob, int id);

    void deleteAlumniById(int id);

    void removeAllAlumni(String type);
}
