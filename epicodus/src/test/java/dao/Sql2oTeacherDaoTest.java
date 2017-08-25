package dao;

import models.Teacher;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

import static org.junit.Assert.*;

public class Sql2oTeacherDaoTest {
    private Sql2oStudentDao studentDao;
    private Sql2oAlumniDao alumniDao;
    private Sql2oTeacherDao teacherDao;
    private Connection con;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        studentDao = new Sql2oStudentDao(sql2o);
        alumniDao = new Sql2oAlumniDao(sql2o);
        teacherDao = new Sql2oTeacherDao(sql2o);
        con = sql2o.open();
    }
    @After
    public void tearDown() throws Exception {
        con.close();
    }

    @Test
    public void add_AddsANewTeacherToDatabase() throws Exception {
        Teacher teacher = setUpNew();
        int id = teacher.getId();
        teacherDao.add(teacher);
        assertTrue(teacher instanceof Teacher);
        assertNotEquals(id, teacher.getId());
    }

    @Test
    public void getAllTeachers_retreivesAllTeachersFromDatabase() throws Exception {
        Teacher teacher = setUpNew();
        Teacher teacher2 = setUpNew2();
        teacherDao.add(teacher);
        teacherDao.add(teacher2);
        List<Teacher> tester = teacherDao.getAllTeachers();
        assertEquals(2, tester.size());
        assertEquals(teacher.getClassesTaught(), tester.get(0).getClassesTaught());

    }

    @Test
    public void findById_returnsTeacherbyId() throws Exception {
        Teacher teacher = setUpNew();
        Teacher teacher2 = setUpNew2();
        teacherDao.add(teacher);
        teacherDao.add(teacher2);
        int search = teacher.getId();
        Teacher found = teacherDao.findById(search);
        assertEquals(teacher.getGender(), found.getGender());
    }

    @Test
    public void updateTeacher() throws Exception {
    }

    @Test
    public void deleteTeacherById() throws Exception {
        Teacher teacher = setUpNew();
        Teacher teacher2 = setUpNew2();
        teacherDao.add(teacher);
        teacherDao.add(teacher2);
        int search = teacher.getId();
        teacherDao.deleteTeacherById(search);
        List<Teacher> tester = teacherDao.getAllTeachers();
        assertEquals(1, tester.size());
    }

    @Test
    public void removeAllTeachers() throws Exception {
    }

    public Teacher setUpNew(){
        return new Teacher("Tommy Bahama", "02102016","Male", "92110",5);
    }
    public Teacher setUpNew2(){
        return new Teacher("Rick Ross", "02112016","Male", "97211",2);
    }
}