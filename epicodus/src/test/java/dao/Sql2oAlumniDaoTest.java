package dao;

import models.Alumni;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

import static org.junit.Assert.*;

public class Sql2oAlumniDaoTest {
    private Sql2oStudentDao studentDao;
    private AlumniDao alumniDao;
    private Connection con; //must be sql2o class conn

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        studentDao = new Sql2oStudentDao(sql2o);
        alumniDao = new Sql2oAlumniDao(sql2o);
        con = sql2o.open();
    }
    @After
    public void tearDown() throws Exception {
        con.close();
    }

    @Test
    public void add_addsNewAlumnitoDatabase() throws Exception{
        Alumni alumni = setNewAlum();
        int id = alumni.getId();
        alumniDao.add(alumni);
        assertTrue(alumni instanceof Alumni);
        assertNotEquals(id, alumni.getId());

    }
    @Test
    public void getAll_addsAlumnitToDatabase() throws Exception {
        Alumni alumni = setNewAlum();
        Alumni alumni1 = setNewAlum();
        alumniDao.add(alumni);
        alumniDao.add(alumni1);
        List<Alumni> test = alumniDao.getAllAlumni(true);
        assertEquals(2, studentDao.getAll().size());
        assertEquals(2, test.size());
    }

    @Test
    public void findById_findsAlumniById() throws Exception {
        Alumni alumni = setNewAlum();
        Alumni alumni1 = setNewAlum2();
        alumniDao.add(alumni);
        alumniDao.add(alumni1);
        int searchId = alumni.getId();
        Alumni search = alumniDao.findById(searchId);
        assertEquals(alumni.getDaysToJob(), search.getDaysToJob());
        assertEquals(alumni.getCurrentJob(), search.getCurrentJob());
    }

    @Test
    public void updateAlumni() throws Exception {
    }

    @Test
    public void deleteAlumniById() throws Exception {
    }

    @Test
    public void removeAllAlumni() throws Exception {
    }

    public Alumni setNewAlum(){
        return new Alumni("Graduate Gradinton", 30, "Hospitality", "Male", "97200", "Java", true, "Developer", 100);
    }
    public Alumni setNewAlum2(){
        return new Alumni("Junior Monster", 23, "Aeronautics", "Female", "97210", "Design", true, "Developer", 100);
    }
}