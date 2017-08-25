package dao;

import dataSources.Student;

import java.util.List;

public interface StudentDao {
    void add(Student student);

    void setTrackId(Student student, int trackId);

    List<Student> getAll();

    Student findbyId(int id);

    Double findAverageAge();

    Integer getCountOfStudentsByTrack(Integer trackId);

    List<Student>getAllStudentsByTrack(Integer trackId);

    String getMostPopularTrack();

    Double genderDistribution(String gender);

    Double getPercentCompleted();

    void update(String name, Integer age, String lastJob, String gender, String zipcode, Integer trackId, Boolean graduated, int id);


    void deleteById(int id);

}
