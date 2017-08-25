package models;

public class Alumni extends Student {
    private String currentJob;
    private Integer daysToJob;


    public Alumni(String name, Integer age, String lastJob, String gender, String zipcode, String currentTrack, Boolean graduated, String currentJob, Integer daysToJob) {
        super(name, age, lastJob, gender, zipcode, currentTrack, graduated);
        this.currentJob = currentJob;
        this.daysToJob = daysToJob;
    }

    //GETTERS
    public String getCurrentJob() {
        return currentJob;
    }
    public Integer getDaysToJob() {
        return daysToJob;
    }

    //SETTERS
    public void setCurrentJob(String currentJob) {
        this.currentJob = currentJob;
    }
    public void setDaysToJob(Integer daysToJob) {
        this.daysToJob = daysToJob;
    }

    //equals and hashcode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Alumni alumni = (Alumni) o;

        if (!currentJob.equals(alumni.currentJob)) return false;
        return daysToJob != null ? daysToJob.equals(alumni.daysToJob) : alumni.daysToJob == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + currentJob.hashCode();
        result = 31 * result + (daysToJob != null ? daysToJob.hashCode() : 0);
        return result;
    }
}
