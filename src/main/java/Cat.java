public class Cat extends Animal {
    boolean striped;

    public Cat(double weight, double height, String name, int age, boolean striped) {
        super(weight, height, name, age);
        this.striped = striped;
    }

    public void scratch() {
        System.out.println("hiss! -_-");
    }

    public boolean isStriped() {
        return striped;
    }

    public void setStriped(boolean striped) {
        this.striped = striped;
    }
}
