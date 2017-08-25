package dao;

import dataSources.Cohort;
import dataSources.Track;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

import static org.junit.Assert.*;

public class Sql2oTrackDaoTest {
    private Sql2oTrackDao trackDao;
    private Sql2oCohortDao cohortDao;
    private Connection con;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        trackDao = new Sql2oTrackDao(sql2o);
        cohortDao = new Sql2oCohortDao(sql2o);
        con = sql2o.open();
    }
    @After
    public void tearDown() throws Exception {
        con.close();
    }

    @Test
    public void add() throws Exception {
        Track track = newTrack();
        trackDao.add(track);
        assertTrue(track instanceof Track);
    }

    @Test
    public void addTrackToCohort() throws Exception {
        Track track = newTrack();
        trackDao.add(track);
        Cohort cohort = setNewCohort();
        cohortDao.add(cohort);
        trackDao.addTrackToCohort(cohort.getCohortId(),track.getTrackId());
    }

    @Test
    public void getAll() throws Exception {
        Track track = newTrack();
        Track track1 = newTrack();
        Track track2 = newTrack();
        Track track3 = newTrack();
        trackDao.add(track);
        trackDao.add(track1);
        trackDao.add(track2);
        trackDao.add(track3);
        List<Track> trackList = trackDao.getAll();
        assertEquals(4, trackList.size());
    }

    @Test
    public void getAllTracksByCohort() throws Exception {
    }

    @Test
    public void findById() throws Exception {
        Track track = newTrack();
        Track track1 = newTrack();
        Track track2 = newTrack();
        Track track3 = newTrack();
        trackDao.add(track);
        trackDao.add(track1);
        trackDao.add(track2);
        trackDao.add(track3);
        int find = track2.getTrackId();
        Track finder = trackDao.findById(find);
        int found = finder.getTrackId();
        assertEquals(find, found);
    }

    @Test
    public void deleteTrack() throws Exception {
        Track track = newTrack();
        Track track1 = newTrack();
        Track track2 = newTrack();
        Track track3 = newTrack();
        trackDao.add(track);
        trackDao.add(track1);
        trackDao.add(track2);
        trackDao.add(track3);
        int delete = track3.getTrackId();
        trackDao.deleteTrack(delete);
        assertEquals(3, trackDao.getAll().size());
    }

    public Track newTrack(){
        return new Track("JAVA/ANDROID","Java/JavaScript/Android/Internship","20 weeks");
    }
    public Cohort setNewCohort(){
        return new Cohort("Fall 2017","Portland, Or", "07/21/2017");
    }

}