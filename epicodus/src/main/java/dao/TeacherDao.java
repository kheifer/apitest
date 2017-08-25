package dao;

import models.Teacher;

import java.util.List;

public interface TeacherDao {

    void add(Teacher teacher);

    void addTeacherToCohort(String CohortName, int id);
    List<Teacher> getAllTeachers();

    Teacher findById(int id);


    void updateTeacher(String name, String hireDate, String gender, String zipcode, Integer classesTaught, int id);

    void deleteTeacherById(int id);

    void removeAllTeachers();
}
