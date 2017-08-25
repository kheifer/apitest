package dataSources;

public class Cohort {
    private String cohortName;
    private String location;
    private int cohortId;
    private String dateStarted;

    public Cohort(String cohortName, String location, String dateStarted) {
        this.cohortName = cohortName;
        this.location = location;
        this.dateStarted = dateStarted;
    }

    //GETTERS and SETTERS

    public String getCohortName() {
        return cohortName;
    }
    public void setCohortName(String cohortName) {
        this.cohortName = cohortName;
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public int getCohortId() {
        return cohortId;
    }
    public void setCohortId(int cohortId) {
        this.cohortId = cohortId;
    }

    public String getDateStarted() {
        return dateStarted;
    }
    public void setDateStarted(String dateStarted) {
        this.dateStarted = dateStarted;
    }

    //equals and hashcode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cohort cohort = (Cohort) o;

        if (!cohortName.equals(cohort.cohortName)) return false;
        if (!location.equals(cohort.location)) return false;
        return dateStarted.equals(cohort.dateStarted);
    }

    @Override
    public int hashCode() {
        int result = cohortName.hashCode();
        result = 31 * result + location.hashCode();
        result = 31 * result + dateStarted.hashCode();
        return result;
    }
}
