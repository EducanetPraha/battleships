package shipgame.domain;


import shipgame.domain.types.FieldTypes;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by Tomáš Rechtig on 24.10.2016.
 */
public interface Listener {

    Scanner scanner = new Scanner(System.in);

    /**
     *  Utility methods
     */
    static void printMap(FieldTypes[][] map){
        int i = 1, j = 1;
        System.out.print("y|x ");
        for (FieldTypes[] c : map){
            System.out.print(j + ") ");
            j++;
        }
        System.out.println();
        for (FieldTypes[] column : map) {
            System.out.print(i + ")  ");
            i++;
            for (FieldTypes row : column) {
                System.out.print(row.getIndicator() + "  ");
            }
            System.out.println();
        }
    }

    static void printMapForPlayer(Player player){
        System.out.println("\n" + player.getName() + "'s map");
        Listener.printMap(player.getMap());
    }

    /**
     *  Listeners for user input
     */
    static int[] listenForCoords(int min, int max) {
        return new int[]{listenForIntWithin(min, max), listenForIntWithin(min, max)};
    }

    static int listenForIntWithin(int min, int max) {
        int scanned = listenForInt();
        if(scanned < max && scanned > min) return scanned;
        else System.out.println("Invalid input!"); return listenForIntWithin(min, max);
    }

    static int listenForInt() {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException err){
            scanner.next();
            System.out.println("Invalid input!");
        }
        return listenForInt();
    }

    static String listenForString() {
        return scanner.nextLine();
    }

    /**
     *  On event listeners
     */
    static void onNewRound(Player player){
        System.out.println("\n" + player.getName() + "'s turn!");
        System.out.println("Enter two numbers representing coordinates. First X, then Y.");
    }

    static void onAttack(Player player, Player attacked, int[] coords){
        System.out.println(player.getName() + " is attacking!!! on " + attacked.getName() + "'s position at [" + coords[0] + ", " + coords[1] + "]");
    }

    static void onRoundEnd() {
        System.out.println("Enter any key to proceed!");
        scanner.next();
    }

    static Player onShipSetup(Player player) {
        System.out.println(player.getName());

        return player;
    }

    static Ship onInvalidCoords(Ship ship) {
        System.out.println("Invalid coordinates!");
        return ship;
    }

}
