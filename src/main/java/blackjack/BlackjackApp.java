package blackjack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BlackjackApp {
    public static void main(String[] args) throws IOException {
        final BufferedReader terminalSource = new BufferedReader(new InputStreamReader(System.in));
        BlackjackGame game = new BlackjackGame();
        System.out.println(game.toDisplayString());

        while (true) {
            System.out.print("> ");
            final String line = terminalSource.readLine().trim();
            final int delimiterPosition = line.indexOf(' ');
            final String command = (delimiterPosition == -1 ? line : line.substring(0, delimiterPosition)).trim();
            final String arguments = (delimiterPosition == -1 ? "" : line.substring(delimiterPosition + 1)).trim();

            try {
                switch (command.toLowerCase()) {
                    case "reset":
                        processResetCommand(game, arguments);
                        break;
                    case "roll":
                        processRollCommand(game, arguments);
                        break;
                    case "stand":
                        processStandCommand(game, arguments);
                        break;
                    case "exit": // fallthrough
                    case "stop": // fallthrough
                    case "quit":
                        System.out.println("Bye!");
                        return;
                    default:
                        processHelpCommand(arguments);
                        break;
                }
            } catch (final Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void processHelpCommand(final String arguments) {
        System.out.println("List of available commands:");
        System.out.println("- help: displays this help");
        System.out.println("- roll: rolls a die");
        System.out.println("- stand: stop rolling and let the dealer roll");
        System.out.println("- reset: new game");
        System.out.println("- quit: terminates this program");
    }

    private static void processRollCommand(BlackjackGame game, String arguments) {
        int rollAmount = 1;

        if (arguments != null && !arguments.isBlank()) {    // check if arguments is not null or empty
            try {
                rollAmount = Integer.parseInt(arguments);   // try to parse arguments as integer to determine amount of rolls
            } catch (NumberFormatException e) {             // catch the error of parsing something that is not a number
                System.out.println("Invalid amount of dice rolls, rolling once instead. Type in help for further information");
            }
        }

        if (rollAmount > 5) {   // check if amount of rolls is higher than 5 as it makes no sense to roll that often
            System.out.println("You can only roll up to 5 dice at once. Rolling 5 dice instead.");
            rollAmount = 5;
        }

        for (int i = 0; i < rollAmount; i++) {  // roll the dice for the player
            game.rollPlayer();
            System.out.println("Rolled a " + game.getPlayerRolls()[game.getPlayerRolls().length - 1] + "!"); // fetching the last int from the int array
        }

        System.out.println("Total score: " + game.getPlayerTotal());
        if (game.getPlayerTotal() > 21) {
            System.out.println("You have lost the game!");
            System.out.println("Type quit or reset.");
        }
    }

    private static void processStandCommand(BlackjackGame game, String arguments) {
        game.rollDealer();
        int playerScore = game.getPlayerTotal();
        int dealerScore = game.getDealerTotal();
        int[] playerDice = game.getPlayerRolls();
        int[] dealerDice = game.getDealerRolls();

        System.out.println("The dealer rolled " + Arrays.toString(dealerDice) + " for a total of " + dealerScore + ".");
        System.out.println("You rolled " + Arrays.toString(playerDice) + " for a total of " + playerScore + ".");

        if (dealerScore > 21) {
            System.out.println("The dealer bombed! You won.");
        } else if (playerScore > dealerScore) {
            System.out.println("You won! Good job being lucky.");
        } else if (dealerScore > playerScore) {
            System.out.println("You lost! Try harder next time, won't you?");
        } else {
            System.out.println("You're even. Play again to determine the true winner :)");
        }
        System.out.println("Type quit or reset.");
    }

    private static void processResetCommand(BlackjackGame game, String arguments) {
        game.reset();
        System.out.println("Starting new game! Good luck :)");
    }
}
