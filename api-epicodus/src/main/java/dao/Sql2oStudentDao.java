package dao;

import dataSources.Student;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oStudentDao implements StudentDao{
    private final Sql2o sql2o;

    public Sql2oStudentDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public void add(Student student) {
        String query = "INSERT into students (name, age, lastJob, gender, trackId, zipcode, graduated) VALUES (:name, :age, :lastJob, :gender, :trackId, :zipcode, :graduated)";
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(query)
                    .bind(student)
                    .throwOnMappingFailure(false)
                    .executeUpdate()
                    .getKey();
            student.setId(id);
        }catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void setTrackId(Student student, int trackId) {
        String query = "INSERT into students (trackId) VALUES (:trackId) WHERE id = :id";
        try(Connection con = sql2o.open()){
            con.createQuery(query)
                    .addParameter("id", student.getId())
                    .addParameter("trackId", trackId)
                    .throwOnMappingFailure(false)
                    .executeUpdate();
        }
    }

    @Override
    public List<Student> getAll() {
        String query = "SELECT * FROM students";
        try(Connection con = sql2o.open()){
            return con.createQuery(query)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Student.class);
        }
    }

    @Override
    public Student findbyId(int id) {
        String query = "SELECT * FROM students WHERE id = :id";
        try(Connection con = sql2o.open()){
            return con.createQuery(query)
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(Student.class);
        }
    }

    @Override
    public Double findAverageAge() {
        String query = "SELECT AVG(age) FROM students";
        try(Connection con = sql2o.open()){
            return con.createQuery(query)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(Double.class);
        }
    }

    @Override
    public Integer getCountOfStudentsByTrack(Integer trackId) {
        String query = "SELECT COUNT(*) FROM students WHERE trackId = :trackId";
        try(Connection con = sql2o.open()){
            return con.createQuery(query)
                    .addParameter("trackId", trackId)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(Integer.class);
        }
    }

    @Override
    public List<Student> getAllStudentsByTrack(Integer trackId) {
        String query = "SELECT * FROM students WHERE trackId = :trackId";
        try(Connection con = sql2o.open()){
            return con.createQuery(query)
                    .addParameter("trackId", trackId)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Student.class);
        }
    }

    @Override
    public String getMostPopularTrack() {
        String query = "SELECT trackId FROM students GROUP BY trackId ORDER BY COUNT(*) DESC LIMIT 1";
        try(Connection con = sql2o.open()){
            Integer search= (Integer) con.createQuery(query)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(Integer.class);
            return con.createQuery("SELECT focus FROM tracks WHERE trackId = :trackId")
                    .addParameter("trackId",search)
                    .executeAndFetchFirst(String.class);
        }
    }

    @Override
    public Double genderDistribution(String gender) {
//        String query = "SELECT (COUNT(gender)* 100 / (SELECT COUNT(*) FROM students)) as SCORE FROM students WHERE gender = :gender";
        try(Connection con = sql2o.open()) {
            Double total = (double) con.createQuery("SELECT COUNT(*) FROM students")
                    .executeAndFetchFirst(Double.class);
            Double numberOfgender = (double) con.createQuery("SELECT COUNT(*) FROM students WHERE gender = :gender")
                    .addParameter("gender", gender)
                    .executeAndFetchFirst(Double.class);
            return (double) ((numberOfgender / total) * 100 );
        }
    }

    @Override
    public Double getPercentCompleted() {
        try(Connection con = sql2o.open()) {
            Double total = (double) con.createQuery("SELECT COUNT(*) FROM students")
                    .executeAndFetchFirst(Double.class);
            Double numberOfStudentsCompleted = (double) con.createQuery("SELECT COUNT(*) FROM students WHERE graduated = :graduated")
                    .addParameter("graduated", true)
                    .executeAndFetchFirst(Double.class);
            return (double) ((numberOfStudentsCompleted / total) * 100 );
        }
    }

    @Override
    public void update(String name, Integer age, String lastJob, String gender, String zipcode, Integer trackId, Boolean graduated, int id) {
        String query = "UPDATE students SET(name, age, lastJob, gender, zipcode, trackId, graduated) = (:name, :age, :lastJob, :gender, :zipcode, :trackId, :graduated) WHERE id = :id";
        try(Connection con = sql2o.open()){
            con.createQuery(query)
                    .addParameter("name", name)
                    .addParameter("age", age)
                    .addParameter("lastJob", lastJob)
                    .addParameter("gender", gender)
                    .addParameter("zipcode", zipcode)
                    .addParameter("trackId", trackId)
                    .addParameter("graduated", graduated)
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeUpdate();
        }
    }

    @Override
    public void deleteById(int id) {
        String query = "DELETE FROM students WHERE id = :id";
        try(Connection con = sql2o.open()){
            con.createQuery(query)
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeUpdate();
        }
    }
}