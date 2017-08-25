package models;

public class Alumni extends Student {
    private String currentJob;
    private Integer daystoJob;


    public Alumni(String name, Integer age, String lastJob, String gender, String zipcode, String currentTrack, Boolean graduated, String currentJob, Integer daystoJob) {
        super(name, age, lastJob, gender, zipcode, currentTrack, graduated);
        this.currentJob = currentJob;
        this.daystoJob = daystoJob;
    }

    //GETTERS
    public String getCurrentJob() {
        return currentJob;
    }
    public Integer getDaystoJob() {
        return daystoJob;
    }

    //SETTERS
    public void setCurrentJob(String currentJob) {
        this.currentJob = currentJob;
    }
    public void setDaystoJob(Integer daystoJob) {
        this.daystoJob = daystoJob;
    }

    //equals and hashcode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Alumni alumni = (Alumni) o;

        if (!currentJob.equals(alumni.currentJob)) return false;
        return daystoJob != null ? daystoJob.equals(alumni.daystoJob) : alumni.daystoJob == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + currentJob.hashCode();
        result = 31 * result + (daystoJob != null ? daystoJob.hashCode() : 0);
        return result;
    }
}
