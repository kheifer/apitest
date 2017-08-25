package models;

public class Cohort {
    private String cohortName;
    private String location;
    private int cohortId;

    public Cohort(String cohortName, String location){
        this.cohortName = cohortName;
        this.location = location;
    }

    //GETTERS

    public String getCohortName() {
        return cohortName;
    }
    public String getLocation() {
        return location;
    }
    public int getCohortId() {
        return cohortId;
    }

    //SETTER
    public void setCohortName(String cohortName) {
        this.cohortName = cohortName;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public void setCohortId(int cohortId) {
        this.cohortId = cohortId;
    }

    //hashcode and equals

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cohort cohort = (Cohort) o;

        if (!cohortName.equals(cohort.cohortName)) return false;
        return location.equals(cohort.location);
    }

    @Override
    public int hashCode() {
        int result = cohortName.hashCode();
        result = 31 * result + location.hashCode();
        return result;
    }
}
