package models;

public class Teacher {
    private String name;
    private String hireDate;
    private String gender;
    private String zipcode;
    private Integer classesTaught;
    private int id;

    public Teacher(String name, String hireDate, String gender, String zipcode, Integer classesTaught){
        this.name = name;
        this.hireDate =hireDate;
        this.gender = gender;
        this.zipcode = zipcode;
        this.classesTaught = classesTaught;
    }

    //GETTTERS

    public String getName() {
        return name;
    }
    public String getHireDate() {
        return hireDate;
    }
    public String getGender() {
        return gender;
    }
    public String getZipcode() {
        return zipcode;
    }
    public Integer getClassesTaught() {
        return classesTaught;
    }
    public int getId() {
        return id;
    }

    //SETTER

    public void setName(String name) {
        this.name = name;
    }
    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
    public void setClassesTaught(Integer classesTaught) {
        this.classesTaught = classesTaught;
    }
    public void setId(int id) {
        this.id = id;
    }

    //hashcode and equals

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Teacher teacher = (Teacher) o;

        if (!name.equals(teacher.name)) return false;
        if (!hireDate.equals(teacher.hireDate)) return false;
        if (!gender.equals(teacher.gender)) return false;
        if (!zipcode.equals(teacher.zipcode)) return false;
        return classesTaught.equals(teacher.classesTaught);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + hireDate.hashCode();
        result = 31 * result + gender.hashCode();
        result = 31 * result + zipcode.hashCode();
        result = 31 * result + classesTaught.hashCode();
        return result;
    }
}
