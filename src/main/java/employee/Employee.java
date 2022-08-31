package employee;
// TODO: change into JavaBean class featuring encapsulation
// Add gender as instance variable of an enum type
// (later use valueOf(String) and name() to convert the enum
// instances from/to text)

public class Employee {
    private long identity;
    private String surname;
    private String forename;
    private float age;

    public Employee() {
    }

    public long getIdentity() {
        return identity;
    }

    public void setIdentity(long identity) {
        this.identity = identity;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public float getAge() {
        return age;
    }

    public void setAge(float age) {
        this.age = age;
    }
}
