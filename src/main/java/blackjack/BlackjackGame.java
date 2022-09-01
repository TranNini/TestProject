package blackjack;

import java.util.Arrays;
import java.util.Random;

public class BlackjackGame {
    public final static int[] BLACK_JACK = {7, 7, 7};
    public final static int DIE_FACE_COUNT = 10;

    private int[] playerRolls = {};
    private int[] dealerRolls = {};
    private Random die = new Random();

    public int[] getPlayerRolls() {
        return playerRolls;
    }

    public int[] getDealerRolls() {
        return dealerRolls;
    }

    public int getPlayerTotal() {
        int sum = 0;
        for (int i = 0; i < playerRolls.length; i++) {  // go through all player dice
            sum += playerRolls[i];                      // and add them up
        }
        return sum;
    }

    public int getDealerTotal() {
        int sum = 0;
        for (int i = 0; i < dealerRolls.length; i++) {  // go through all dealer dice
            sum += dealerRolls[i];                      // and add them up
        }
        return sum;
    }

    public void rollPlayer() {
        int roll = die.nextInt(1, DIE_FACE_COUNT);    // rolling a random number from 1 up to DIE_FACE_COUNT
        playerRolls = Arrays.copyOf(playerRolls, playerRolls.length + 1);   // creating new Array with +1 space for new die
        playerRolls[playerRolls.length - 1] = roll;         // adding value of roll to the last space of array
    }

    public void rollDealer() {
        while (getDealerTotal() < 16) {
            int roll = die.nextInt(1, DIE_FACE_COUNT);    // rolling a random number from 1 up to DIE_FACE_COUNT
            dealerRolls = Arrays.copyOf(dealerRolls, dealerRolls.length + 1);   // creating new Array with +1 space for new die
            dealerRolls[dealerRolls.length - 1] = roll;         // adding value of roll to the last space of array
        }
    }

    public String toDisplayString() {
        return "Game of Blackjack! Try to get as close to 21 to win by rolling a " + DIE_FACE_COUNT + " sided die.\n" +
                "If you roll higher than 21, you automatically lose.\n" +
                "Roll by using the 'roll' command and optionally add a number ('roll 3') to roll multiple times.\n" +
                "Good luck :)";
    }

    public void reset() {
        playerRolls = new int[]{};  // creating empty array for player dice
        dealerRolls = new int[]{};  // creating empty array for dealer dice
    }
}
