package dao;

import models.Cohort;
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
        String query = "INSERT into cohorts (cohortName, cohortLocation) VALUES (:cohortName, :cohortLocation)";
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
        String query = "SELECT * FROM cohorts WHERE id = :id";
        try(Connection con = sql2o.open()){
            return con.createQuery(query)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Cohort.class);
        }
    }

    @Override
    public void updateCohort(String cohortName, String cohortLocation, int cohortId) {

    }

    @Override
    public void deleteCohortById(int id) {
        String query = "DELETE FROM cohorts WHERE id = :id";
        try(Connection con = sql2o.open()){
            con.createQuery(query)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }
}
