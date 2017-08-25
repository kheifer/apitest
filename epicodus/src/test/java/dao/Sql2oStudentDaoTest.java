package dao;
import models.Student;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class Sql2oStudentDaoTest {
    private Sql2oStudentDao studentDao;
    private Connection con; //must be sql2o class conn

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        studentDao = new Sql2oStudentDao(sql2o);
        con = sql2o.open();
    }
    @After
    public void tearDown() throws Exception {
        con.close();
    }

    @Test
    public void add_putsNewStudentInDatabase() throws Exception {
        Student student = createStudent();
        int id = student.getId();
        studentDao.add(student);
        assertTrue(student instanceof Student);
        assertNotEquals(id, student.getId());
    }

    @Test
    public void getAll_retreivesAllStudents() throws Exception {
        Student student = createStudent();
        Student student1 = createStudent();
        studentDao.add(student);
        studentDao.add(student1);
        List<Student> test = studentDao.getAll();
        assertEquals(2, studentDao.getAll().size());
    }

    @Test
    public void findbyId_retreivesInstanceOfStudentById() throws Exception {
        Student student = createStudent();
        Student student1 = createStudent2();
        studentDao.add(student);
        studentDao.add(student1);
        int id = student.getId();
        Student finder = studentDao.findbyId(id);
        assertEquals(student.getAge(), finder.getAge());
    }

    @Test
    public void update_updatesInstanceofStudentById() throws Exception {
        Student student = createStudent();
        Student student1 = createStudent2();
        studentDao.add(student);
        studentDao.add(student1);
        int updateId = student.getId();
        studentDao.update("Walter White",55, "Pharmacist", "Male", "97211", "Java",false, updateId);
        assertNotEquals(student.getAge(), studentDao.findbyId(updateId).getAge());
    }

    @Test
    public void deleteById_deletesInstanceofStudentById() throws Exception {
        Student student = createStudent();
        Student student1 = createStudent2();
        studentDao.add(student);
        studentDao.add(student1);
        int id = student.getId();
        studentDao.deleteById(id);
        assertEquals(1, studentDao.getAll().size());
    }
    @Test
    public void findAverageAge_findsTheAvergaeAgeOfStudents() throws Exception {
        Student student = createStudent();
        Student student1 = createStudent2();
        studentDao.add(student);
        studentDao.add(student1);
        Double answer = studentDao.findAverageAge();
        Double expected = (double)(student.getAge()+student1.getAge())/2;
        assertEquals(expected , answer);
    }
    @Test
    public void getMostPopularTrack_retursMostPopularTrack() throws Exception{
        Student student = createStudent();
        Student student1 = createStudent2();
        Student student2 = createStudent();
        Student student3 = createStudent();
        Student student4 = createStudent2();
        studentDao.add(student);
        studentDao.add(student1);
        studentDao.add(student2);
        studentDao.add(student3);
        studentDao.add(student4);
        String answer = studentDao.getMostPopularTrack();
        assertEquals(student.getCurrentTrack(), answer);
    }
//    @Test
//    public void sortByGender() throws Exception{
//            Student student = createStudent();
//            Student student1 = createStudent2();
//            Student student2 = createStudent();
//            Student student3 = createStudent();
//            Student student4 = createStudent2();
//            studentDao.add(student);
//            studentDao.add(student1);
//            studentDao.add(student2);
//            studentDao.add(student3);
//            studentDao.add(student4);
//            List<String> answer = studentDao.genderDistribution();
//            System.out.println(answer);
//    }

    public Student createStudent(){
        return new Student("Max Pass",30, "Biology", "Male", "97211", "Java",false);
    }
    public Student createStudent2(){
        return new Student("Scary Terry",20, "Food Service", "Female", "97211", "python",false);
    }
}