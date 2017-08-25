package models;

public class Student {
    private String name;
    private Integer age;
    private String lastJob;
    private String gender;
    private String zipcode;
    private int trackId;
    private int id;
    private Boolean graduated;

    public Student(String name, Integer age, String lastJob, String gender, String zipcode, int trackId, Boolean graduated){
        this.name = name;
        this.age = age;
        this.lastJob = lastJob;
        this.gender = gender;
        this.zipcode = zipcode;
        this.trackId = trackId;
        this.graduated = graduated;
    }

    //GETTERS

    public String getName() {
        return name;
    }
    public Integer getAge() {
        return age;
    }
    public String getLastJob() {
        return lastJob;
    }
    public String getGender() {
        return gender;
    }
    public String getZipcode() {
        return zipcode;
    }
    public int trackId() {
        return trackId;
    }
    public int getId() {
        return id;
    }
    public Boolean getGraduated() {
        return graduated;
    }

    //SETTERS

    public void setName(String name) {
        this.name = name;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public void setLastJob(String lastJob) {
        this.lastJob = lastJob;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
    public void setTrackId(int trackId) {
        this.trackId = trackId;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setGraduated(Boolean graduated) {
        this.graduated = graduated;
    }

    //equals and hashcode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (!name.equals(student.name)) return false;
        if (!age.equals(student.age)) return false;
        if (!lastJob.equals(student.lastJob)) return false;
        if (!gender.equals(student.gender)) return false;
        if (!zipcode.equals(student.zipcode)) return false;
        return graduated.equals(student.graduated);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + age.hashCode();
        result = 31 * result + lastJob.hashCode();
        result = 31 * result + gender.hashCode();
        result = 31 * result + zipcode.hashCode();
        result = 31 * result + graduated.hashCode();
        return result;
    }
}
