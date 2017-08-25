package dao;

import models.Student;

import java.util.List;

public class Sql2oStudentDao implements StudentDao {
    @Override
    public void add(Student student) {

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
