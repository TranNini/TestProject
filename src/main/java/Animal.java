public class Animal {
    double weight;
    double height;
    String name;
    int age;

    public Animal() {
    }

    public Animal(double weight, double height, String name, int age) {
        this.weight = weight;
        this.height = height;
        this.name = name;
        this.age = age;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "weight=" + weight +
                ", height=" + height +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
