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
        return null;
    }

    @Override
    public Cohort findById(int id) {
        return null;
    }

    @Override
    public void updateCohort(String cohortName, String cohortLocation, String dateStarted, int cohortId) {

    }

    @Override
    public void deleteCohortById(int id) {

    }
}
