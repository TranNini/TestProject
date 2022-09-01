package tictactoe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * Facade for the interactive TicTacToe text application,
 * based on object-based programming techniques and exception handling.
 */
public class TicTacToeApp {

    /**
     * Application entry point.
     * @param args the runtime arguments
     * @throws IOException
     */
    static public void main (final String[] args) throws IOException {
        final BufferedReader terminalSource = new BufferedReader(new InputStreamReader(System.in));
        TicTacToeBoard board = new TicTacToeBoard();

        while (true) {
            System.out.println(board.toDisplayString());
            System.out.print("> ");
            final String line = terminalSource.readLine().trim();
            final int delimiterPosition = line.indexOf(' ');
            final String command = (delimiterPosition == -1 ? line : line.substring(0, delimiterPosition)).trim();
            final String arguments = (delimiterPosition == -1 ? "" : line.substring(delimiterPosition + 1)).trim();


            try {
                switch (command.toLowerCase()) {
                    case "reset":
                        processResetCommand(board);
                        break;
                    case "set":
                        processSetCommand(board,arguments);
                        break;
                    case "quit":
                        System.out.println("Bye!");
                        return;
                    default:
                        processHelp(arguments);
                        break;
                }
            } catch (final Exception e) {
                String message = e.getClass().getSimpleName();
                if (e.getMessage() != null) message += ": " + e.getMessage();
                System.err.println(message);

                //e.printStackTrace();
            }
        }
    }


    /**
     * Displays the available commands.
     * @param arguments the command arguments
     */
    static public void processHelp (final String arguments) {
        System.out.println("List of available commands:");
        System.out.println("- help: displays this help");
        System.out.println("- set <rank> <file>: sets the current opponent using zero based coordinates");
        System.out.println("- reset: resets the board");
        System.out.println("- quit: terminates this program");
    }

    static public void processSetCommand(TicTacToeBoard board, String arguments) {
        final String[] values = arguments.split(" ");
        if (values.length != 2) throw new IllegalArgumentException("set command needs exactly 2 arguments <rank> <file>");
        int rank = Integer.parseInt(values[0]);
        int file = Integer.parseInt(values[1]);

        if ( rank < 0 | rank >= board.getContent()[0].length) {
            System.out.println("Illegal rank value: " + rank);
        } else if ( file < 0 | file >= board.getContent().length) {
            System.out.println("Illegal file value: " + file);
        } else board.set(rank,file);
    }
    static public void processResetCommand(TicTacToeBoard board) {
        board.reset();
    }
}