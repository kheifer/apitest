package dataSources;

public class Student {
    private String name;
    private Integer age;
    private String lastJob;
    private String gender;
    private String zipcode;
    private int trackId;
    private int id;
    private Boolean graduated;

    public Student(String name, Integer age, String lastJob, String gender, int trackId, String zipcode, Boolean graduated){
        this.name = name;
        this.age = age;
        this.lastJob = lastJob;
        this.gender = gender;
        this.zipcode = zipcode;
        this.graduated = graduated;
        this.trackId = trackId;
    }

    //GETTERS and SETTERS

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }

    public String getLastJob() {
        return lastJob;
    }
    public void setLastJob(String lastJob) {
        this.lastJob = lastJob;
    }

    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getZipcode() {
        return zipcode;
    }
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public int getTrackId() {
        return trackId;
    }
    public void setTrackId(int trackId) {
        this.trackId = trackId;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Boolean getGraduated() {
        return graduated;
    }
    public void setGraduated(Boolean graduated) {
        this.graduated = graduated;
    }
    //Equals and hashcode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (trackId != student.trackId) return false;
        if (name != null ? !name.equals(student.name) : student.name != null) return false;
        if (age != null ? !age.equals(student.age) : student.age != null) return false;
        if (lastJob != null ? !lastJob.equals(student.lastJob) : student.lastJob != null) return false;
        if (gender != null ? !gender.equals(student.gender) : student.gender != null) return false;
        if (zipcode != null ? !zipcode.equals(student.zipcode) : student.zipcode != null) return false;
        return graduated != null ? graduated.equals(student.graduated) : student.graduated == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (lastJob != null ? lastJob.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (zipcode != null ? zipcode.hashCode() : 0);
        result = 31 * result + trackId;
        result = 31 * result + (graduated != null ? graduated.hashCode() : 0);
        return result;
    }
}
