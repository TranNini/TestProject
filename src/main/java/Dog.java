public class Dog extends Animal {
    String breed;

    public Dog(double weight, double height, String name, int age, String breed) {
        super(weight, height, name, age);
        this.breed = breed;
    }

    public void bark() {
        System.out.println("woof woof!");
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "breed='" + breed + '\'' +
                ", weight=" + weight +
                ", height=" + height +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
