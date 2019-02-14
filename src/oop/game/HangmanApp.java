package oop.game;

import java.util.Scanner;

public class HangmanApp {

    public static void main(String[] args) {
        welcome();
        process();
        exit();
    }

    public static void welcome() {
        System.out.println("Welcome, let's play hangman!");
        System.out.print("Here is the word I am thinking of: ");
    }

    public static void process() {
        Game game = new Game();
        String word = game.getWord();
        System.out.print(game.getBlanks());
        System.out.println();

        Scanner keyboard = new Scanner(System.in);

        int i = 1;
        while (i < 7) {
            System.out.print("\nEnter your guess or $ for Lifeline: ");
            game.setGuess(keyboard.next());

            if (word.contains(game.getGuess()) || game.getGuess().equals("$")) {
                System.out.println(game.playGame());
                if (game.getBuilder().indexOf("-") == -1) {
                    System.out.println(game.wonGame());
                    break;
                }
            } else {
                System.out.println("You have guessed incorrectly " + i + "/6 times.");
                System.out.println(game.getStatus());
                if (i == 6) {
                    System.out.println(game.looseGame());
                }
                i++;
            }
        }
    }

    public static void exit() {
        System.out.println("\nThank you for playing!");
    }
}
