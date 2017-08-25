package dao;

import models.Alumni;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oAlumniDao implements AlumniDao {
    private final Sql2o sql2o;

    public Sql2oAlumniDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Alumni alumni) {
        String query = "INSERT into students (name, age, lastJob, gender, zipcode, currentTrack, graduated, currentJob, daysToJob) VALUES (:name, :age, :lastJob, :gender, :zipcode, :currentTrack, :graduated, :currentJob, :daysToJob)";
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(query)
                    .bind(alumni)
                    .throwOnMappingFailure(false)
                    .executeUpdate()
                    .getKey();
            alumni.setId(id);
        }catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public List<Alumni> getAll(Boolean graduated) {
        return null;
    }

    @Override
    public Alumni findById(int id) {
        return null;
    }

    @Override
    public void updateAlumni(String name, Integer age, String lastJob, String gender, String zipcode, String currentTrack, String currentJob, Integer daysToJob, int id) {
    }

    @Override
    public void deleteAlumniById(int id) {

    }

    @Override
    public void removeAllAlumni(String type) {

    }
}
