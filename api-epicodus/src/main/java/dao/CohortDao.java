package dao;

import dataSources.Cohort;

import java.util.List;

public interface CohortDao {
    void add(Cohort cohort);

    List<Cohort> getAll();

    Cohort findById(int id);

    void updateCohort(String cohortName, String cohortLocation, int cohortId);

    void deleteCohortById(int id);
}
