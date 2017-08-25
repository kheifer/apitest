package models;

public class Alumni extends Student {
    private String currentJob;
    private Integer daysToJob;
    private Integer salary;


    public Alumni(String name, Integer age, String lastJob, String gender, String zipcode, Integer trackId, Boolean graduated, String currentJob, Integer daysToJob) {
        super(name, age, lastJob, gender, zipcode, trackId, graduated);
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

    public Integer getSalary() {
        return salary;
    }

    //SETTERS
    public void setCurrentJob(String currentJob) {
        this.currentJob = currentJob;
    }
    public void setDaysToJob(Integer daysToJob) {
        this.daysToJob = daysToJob;
    }
    public void setSalary(Integer salary){ this.salary = salary; }
    //equals and hashcode

    //equals and hashcode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Alumni alumni = (Alumni) o;

        if (!currentJob.equals(alumni.currentJob)) return false;
        if (!daysToJob.equals(alumni.daysToJob)) return false;
        return salary.equals(alumni.salary);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + currentJob.hashCode();
        result = 31 * result + daysToJob.hashCode();
        result = 31 * result + salary.hashCode();
        return result;
    }
}
