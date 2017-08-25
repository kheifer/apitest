package dao;

import javafx.util.Pair;
import models.Student;

import java.util.List;
import java.util.Map;

public interface StudentDao {
    void add(Student student);

    List<Student> getAll();

    Student findbyId(int id);

    Double findAverageAge();

    List<Student> getAllStudentsByTrack(String currentTrack);

    String getMostPopularTrack();

    Double genderDistribution(String gender);



    void update(String name, Integer age, String lastJob, String gender, String zipcode, String currentTrack, Boolean graduated, int id);


    void deleteById(int id);

}
