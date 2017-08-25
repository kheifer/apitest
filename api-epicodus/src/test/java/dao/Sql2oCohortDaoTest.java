package dao;

import dataSources.Cohort;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

import static org.junit.Assert.*;


public class Sql2oCohortDaoTest {
    private Sql2oCohortDao cohortDao;
    private Connection con;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        cohortDao = new Sql2oCohortDao(sql2o);
        con = sql2o.open();
    }
    @After
    public void tearDown() throws Exception {
        con.close();
    }
    @Test
    public void add_AddsNewCohortToDatabase() throws Exception {
        Cohort newCohort = setNewCohort();
        int id = newCohort.getCohortId();
        cohortDao.add(newCohort);
        assertTrue(newCohort instanceof Cohort);
        assertNotEquals(id, newCohort.getCohortId());
    }

    @Test
    public void getAll_ReteivesAllInstancesOfCohort() throws Exception {
        Cohort newCohort = setNewCohort();
        Cohort newCohort1= setNewCohort();
        cohortDao.add(newCohort);
        cohortDao.add(newCohort1);
        List<Cohort> cohortList = cohortDao.getAll();
        assertEquals(2, cohortList.size());
    }

    @Test
    public void findById_findsCohortById() throws Exception {
        Cohort newCohort = setNewCohort();
        Cohort newCohort1= setNewCohort();
        Cohort newCohort2= setNewCohort1();
        Cohort newCohort3= setNewCohort();
        Cohort newCohort4= setNewCohort1();
        cohortDao.add(newCohort);
        cohortDao.add(newCohort1);
        cohortDao.add(newCohort2);
        cohortDao.add(newCohort3);
        cohortDao.add(newCohort4);
        int find =newCohort.getCohortId();
        Cohort findBy = cohortDao.findById(find);
        assertEquals(newCohort.getCohortName(), findBy.getCohortName());
    }

    @Test
    public void updateCohort_updatesCohortInformationById() throws Exception {
        Cohort newCohort1= setNewCohort();
        Cohort newCohort2= setNewCohort1();
        Cohort newCohort3= setNewCohort();
        Cohort newCohort4= setNewCohort1();
        cohortDao.add(newCohort1);
        cohortDao.add(newCohort2);
        cohortDao.add(newCohort3);
        cohortDao.add(newCohort4);
        cohortDao.updateCohort("Spring 2016", "Portland, OR", "09/20/2017", newCohort1.getCohortId());
        assertEquals("Spring 2016", cohortDao.findById(newCohort1.getCohortId()).getCohortName());
    }

    @Test
    public void deleteCohortById() throws Exception {
        Cohort newCohort = setNewCohort();
        Cohort newCohort1= setNewCohort();
        Cohort newCohort2= setNewCohort1();
        Cohort newCohort3= setNewCohort();
        Cohort newCohort4= setNewCohort1();
        cohortDao.add(newCohort);
        cohortDao.add(newCohort1);
        cohortDao.add(newCohort2);
        cohortDao.add(newCohort3);
        cohortDao.add(newCohort4);
        int delete = newCohort1.getCohortId();
        cohortDao.deleteCohortById(delete);
        assertEquals(4, cohortDao.getAll().size());
    }

    public Cohort setNewCohort(){
        return new Cohort("September 2017","Portland, Or","09/17/2017");
    }
    public Cohort setNewCohort1(){
        return new Cohort("Summer 2017","Portland, Or","07/11/2017");
    }
}