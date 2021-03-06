package dao;

import dataSources.Alumni;
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
        String query = "INSERT into students (name, age, lastJob, gender, trackId, zipcode, graduated, currentJob, daysToJob) VALUES (:name, :age, :lastJob, :gender, :trackId, :zipcode, :graduated, :currentJob, :daysToJob)";
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
    public List<Alumni> getAllAlumni() {
        String query = "SELECT * FROM students WHERE graduated = :graduated";
        try(Connection con = sql2o.open()){
            return con.createQuery(query)
                    .addParameter("graduated", true)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Alumni.class);
        }
    }

    @Override
    public Alumni findById(int id) {
        String query = "SELECT * FROM students WHERE id = :id";
        try(Connection con = sql2o.open()){
            return con.createQuery(query)
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(Alumni.class);
        }
    }


    @Override
    public void updateAlumni(String name, Integer age, String lastJob, String gender, Integer trackId, String zipcode, String currentTrack, Boolean graduated, String currentJob, Integer daysToJob, int id) {
        String query = "UPDATE students SET(name, age, lastJob, gender, trackId, zipcode, graduated, currentJob, daysToJob) = (:name, :age, :lastJob, :gender, :trackId, :zipcode, :graduated, :currentJob, :daysToJob) WHERE id = :id";
        try(Connection con = sql2o.open()){
            con.createQuery(query)
                    .addParameter("name", name)
                    .addParameter("age", age)
                    .addParameter("lastJob", lastJob)
                    .addParameter("gender", gender)
                    .addParameter("trackId", trackId)
                    .addParameter("zipcode", zipcode)
                    .addParameter("graduated", graduated)
                    .addParameter("currentJob", currentJob)
                    .addParameter("daysToJob", daysToJob)
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeUpdate();
        }

    }

    @Override
    public void removeAllAlumni() {
        String query = "DELETE FROM students WHERE graduated = :graduated";
        try(Connection con = sql2o.open()){
             con.createQuery(query)
                    .addParameter("graduated", true)
                    .throwOnMappingFailure(false)
                    .executeUpdate();
        }
    }
}
