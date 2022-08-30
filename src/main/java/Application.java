public class Application {
    public static void main(String[] args) {
//        System.out.println("Hello World!");
        IntegerPrinter integerPrinter = new IntegerPrinter(6);
        DoublePrinter doublePrinter = new DoublePrinter(18.0);

        integerPrinter.print();
        doublePrinter.print();

    }
}
