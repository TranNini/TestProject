import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Application {
    public static void main(String[] args) {
//        System.out.println("Hello World!");
        IntegerPrinter integerPrinter = new IntegerPrinter(6);
        DoublePrinter doublePrinter = new DoublePrinter(18.0);

        integerPrinter.print();
        doublePrinter.print();

        Printer<String> printer = new Printer<>("test");
        printer.print();

        Printer<Boolean> booleanPrinter = new Printer<>(true);
        booleanPrinter.print();

        List<Person> personList = new ArrayList<>();
        personList.add(new Person("Phuong", new Date(1381568097000L)));
        System.out.println(personList);

        List<String> stringList = new ArrayList<>();
        stringList.add("test");
        stringList.add("test 2");
        System.out.println(stringList);

        Dog struppi = new Dog(18.2, 86.4, "Struppi", 3, "Dalmatiner");

        struppi.bark();
        System.out.println(struppi.getBreed());
        System.out.println(struppi);

        Animal bello = new Dog(15.3, 75.4, "Bello", 5, "Pudel");
        Dog bello2 = (Dog) bello;
        bello2.bark();

        //does not work
        Animal lori = new Cat(5.1, 37.1, "lori", 2, true);
        Dog lori2 = (Dog) lori;
        lori2.bark();
    }
}
