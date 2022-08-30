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
    }
}
