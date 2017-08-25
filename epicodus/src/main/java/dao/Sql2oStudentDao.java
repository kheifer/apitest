package dao;

import models.Student;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oStudentDao implements StudentDao {
    private final Sql2o sql2o;

    public Sql2oStudentDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public void add(Student student) {
        String query = "INSERT into students (name, age, lastJob, gender, zipcode, currentTrack) VALUES (:name, :age, :lastJob, :gender, :zipcode, :currentTrack)";
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
    public List<Student> getAll() {
        return null;
    }

    @Override
    public Student findbyId(int id) {
        return null;
    }

    @Override
    public void update(String name, Integer age, String lastJob, String gender, String zipcode, String currentTrack, Boolean graduated, int id) {

    }

    @Override
    public void deleteById(int id) {

    }
}
