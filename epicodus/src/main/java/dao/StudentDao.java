package dao;

import models.Student;

import java.util.List;

public interface StudentDao {
    void add(Student student);

    List<Student> getAll();

    Student findbyId(int id);

    void update(String name, Integer age, String lastJob, String gender, String zipcode, String currentTrack, Boolean graduated, int id);

    void deleteById(int id);

}
