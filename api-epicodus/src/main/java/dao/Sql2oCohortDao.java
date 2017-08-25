package dao;

import dataSources.Cohort;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oCohortDao implements CohortDao {
    private final Sql2o sql2o;

    public Sql2oCohortDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public void add(Cohort cohort) {
        String query = "INSERT into cohorts (cohortName, location, dateStarted) VALUES (:cohortName, :location, :dateStarted)";
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(query)
                    .bind(cohort)
                    .executeUpdate()
                    .getKey();
            cohort.setCohortId(id);
        }catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public List<Cohort> getAll() {
        String query = "SELECT * FROM cohorts";
        try(Connection con = sql2o.open()){
            return con.createQuery(query)
                    .executeAndFetch(Cohort.class);
        }
    }

    @Override
    public Cohort findById(int id) {
        String query = "SELECT * FROM cohorts WHERE cohortId = :cohortId";
        try(Connection con = sql2o.open()){
            return con.createQuery(query)
                    .addParameter("cohortId", id)
                    .executeAndFetchFirst(Cohort.class);
        }
    }

    @Override
    public void updateCohort(String cohortName, String cohortLocation, String dateStarted, int cohortId) {
        String query = "UPDATE cohorts SET(cohortName, location, dateStarted) = (:cohortName, :location, :dateStarted) WHERE cohortId = :cohortId";
        try(Connection con = sql2o.open()){
            con.createQuery(query)
                    .addParameter("cohortName", cohortName)
                    .addParameter("location", cohortLocation)
                    .addParameter("dateStarted", dateStarted)
                    .addParameter("cohortId", cohortId)
                    .throwOnMappingFailure(false)
                    .executeUpdate();
        }catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void deleteCohortById(int id) {
        String query = "DELETE FROM cohorts WHERE cohortId = :cohortId";
        try(Connection con = sql2o.open()){
            con.createQuery(query)
                    .addParameter("cohortId", id)
                    .executeUpdate();
        }catch (Sql2oException ex){
            System.out.println(ex);
        }
    }
}
