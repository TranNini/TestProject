package employee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;


/**
 * Facade for the interactive employee text application (version 1),
 * based on object-based programming techniques.
 */
public class EmployeeApp {

    /**
     * Application entry point.
     * @param args the runtime arguments
     * @throws IOException
     */
    static public void main (final String[] args) throws IOException {
        final BufferedReader terminalSource = new BufferedReader(new InputStreamReader(System.in));
        Employee[] employees = {};

        while (true) {
            System.out.print("> ");
            final String line = terminalSource.readLine().trim();
            final int delimiterPosition = line.indexOf(' ');
            final String command = (delimiterPosition == -1 ? line : line.substring(0, delimiterPosition)).trim();
            final String arguments = (delimiterPosition == -1 ? "" : line.substring(delimiterPosition + 1)).trim();

            // TODO: add coarse-grained exception handling here
            switch (command.toLowerCase()) {
                case "add":
                    employees = processAddCommand(employees, arguments);
                    System.out.println("ok.");
                    break;
                case "remove":
                    employees = processRemoveCommand(employees, arguments);
                    System.out.println("ok.");
                    break;
                case "display":
                    processDisplayCommand(employees);
                    System.out.println("ok.");
                    break;
                case "save":
                    processSaveCommand(employees, arguments);
                    System.out.println("ok.");
                    break;
                case "load":
                    employees = processLoadCommand(arguments);
                    System.out.println("ok.");
                    break;
                case "quit":
                    System.out.println("Bye!");
                    return;
                default:
                    processHelp(arguments);
                    break;
            }
        }
    }


    static public void processHelp (final String arguments) {
        System.out.println("Available commands:");
        System.out.println("- add <id>,<surname>,<forename>,<age>: adds a new employee");
        System.out.println("- remove <index>: removes an existing employee");
        System.out.println("- display: displays all existing employees");
        System.out.println("- save <file-path>: saves a text representation of all existing employees");
        System.out.println("- load <file-path>: replaces the employees with new ones based on the given text representation");
        System.out.println("- help: displays this help");
        System.out.println("- quit: terminates this program");
    }


    static public Employee[] processAddCommand (final Employee[] employees, final String arguments) {
        // (A) arguments splitten nach Komma
        // (B) Employee-Instanz erzeugen
        // (C) Instanzvariablen der Employee-Instanz zuweisen, gegebenenfalls Parsen
        // (D) Mittel Arrays.copyOf() eine Kopie von employees erzeugen deren L???nge um eins gr??????er ist
        // (E) Dem letzten Slot des neuen Arrays (N - 1) die Employee-Instanz zuweisen
        // (F) Das neue Array zur???ckgeben

        // TODO:
        // - add check for exactly 4 values - done
        // - handle parse exceptions by assigning 0 respectively NaN as default values
        final String[] values = arguments.split(",");
        if (values.length != 4) {
            System.out.println("Must be 4 arguments!");
            return employees;
        }
        final Employee employee = new Employee();
        employee.setIdentity(Long.parseLong(values[0].trim()));
        employee.setSurname(values[1].trim());
        employee.setForename(values[2].trim());
        employee.setAge(Float.parseFloat(values[3].trim()));

        final Employee[] people = Arrays.copyOf(employees, employees.length + 1);
        people[employees.length] = employee;
        return people;
    }


    static public Employee[] processRemoveCommand (final Employee[] employees, final String arguments) {
        // (A) Arguments parsen und Ergebnis einer Variable "index" zuweisen
        // (B) Hintere Elementeeines nach vorne Kopieren, entweder mittels for-index Schleife
        //     oder mittels System.arraycopy(employees, index + 1, employees, index, employees.length - index - 1)
        // (C) gib Arrays.copyOf(employees, employees.length - 1) zur???ck
        final int index = Integer.parseInt(arguments.trim());
        if (index < 0 | index >= employees.length) {
            System.out.println("Illegal index value: " + index);
            return employees;
        }

        System.arraycopy(employees, index + 1, employees, index, employees.length - index - 1);
        return Arrays.copyOf(employees, employees.length - 1);
    }


    static public void processDisplayCommand (final Employee[] employees) {
        // jeden Employee in eigener Zeile ausgeben, zusammen mit dem zugeh???rigen Index
        for (int index = 0; index < employees.length; ++index) {
            final Employee employee = employees[index];
            System.out.format("% 5d: %s, %s, %s, %s%n", index, employee.getIdentity(), employee.getSurname(), employee.getForename(), employee.getAge());
        }
    }


    static public void processSaveCommand (final Employee[] employees, final String arguments) throws IOException {
        // (A) erzeuge StringBuilder
        // (B) Iteriere ???ber alle employees, und f???ge eine Zeile mit Daten dem StringBuilder hinzu
        // (C) Erzeuge aus arguments eine Path-Instanz, und verwende Files.writeString() um den Inhalt
        //     des StringBuilders in eine UTF-8 encodierte Datei zu schreiben
        final StringBuilder factory = new StringBuilder();
        for (final Employee employee : employees)
            factory.append(String.format("%s, %s, %s, %s%n", employee.getIdentity(), employee.getSurname(), employee.getForename(), employee.getAge()));

        final Path filePath = Paths.get(arguments);
        Files.writeString(filePath, factory, StandardCharsets.UTF_8);
    }


    static public Employee[] processLoadCommand (final String arguments) throws IOException {
        // (A) Erzeuge aus arguments eine Path-Instanz, und verwende Files.readString() um den Text-
        //     Inhalt aus einer UTF-8 encodierten Datei zu lesen
        // (B) Verwende text.split() um diesen Text-Inhalt in Zeilen aufzuteilen
        // (C) Initialisiere die Menge der employees mit leerem Array
        // (D) Iteriere ???ber die Zeilen: Rufe pro Zeile processAddCommand() auf, und merke employees
        // (E) Gib letzten Stand von employees zur???ck
        final Path filePath = Paths.get(arguments);
        final String text = Files.readString(filePath, StandardCharsets.UTF_8);
        final String[] lines = text.split("[\\n\\r]+");

        Employee[] employees = {};
        for (final String line : lines)
            if (!line.isBlank()) employees = processAddCommand(employees, line);

        return employees;
    }
}