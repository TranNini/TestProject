import java.util.Date;

public class Person {
    String name;
    Date birthday;

    public Person() {
    }

    public Person(String name, Date birthday) {
        this.name = name;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Person(name=\"")
                .append(name)
                .append("\", date=\"")
                .append(birthday)
                .append("\")");
        return stringBuilder.toString();
    }
}
