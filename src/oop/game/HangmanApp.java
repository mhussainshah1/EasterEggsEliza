package oop.game;

import java.util.Scanner;

public class HangmanApp {

    public static void main(String[] args) {
        HangmanApp app = new HangmanApp();
        System.out.println(app.welcome());
        app.process();
        System.out.println(app.exit());
    }

    public String welcome() {
        return  "Welcome, let's play hangman!\n"+
                "Here is the word I am thinking of: ";
    }

    public void process() {
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

    public String exit() {
        return "\nThank you for playing!";
    }
}
