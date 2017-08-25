package dao;

import models.Teacher;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oTeacherDao implements TeacherDao {
    private final Sql2o sql2o;

    public Sql2oTeacherDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Teacher teacher) {
        String query = "INSERT into teachers (name, hireDate, gender, zipcode, classesTaught) VALUES (:name, :hireDate, :gender, :zipcode, :classesTaught)";
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(query)
                    .bind(teacher)
                    .throwOnMappingFailure(false)
                    .executeUpdate()
                    .getKey();
            teacher.setId(id);
        }catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public List<Teacher> getAllTeachers() {
        return null;
    }

    @Override
    public Teacher findById(int id) {
        return null;
    }

    @Override
    public void updateTeacher(String name, String hireDate, String gender, String zipcode, Integer classesTaught, int id) {

    }

    @Override
    public void deleteTeacherById(int id) {

    }

    @Override
    public void removeAllTeachers() {

    }
}
