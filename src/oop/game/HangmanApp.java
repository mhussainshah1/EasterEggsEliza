package oop.game;

import oop.ElizaApp;

import java.util.Scanner;

public class HangmanApp {
    private ElizaApp elizaApp;

    //constructor
    public HangmanApp() {

    }

    public HangmanApp(ElizaApp elizaApp) {
        this.elizaApp = elizaApp;
    }
    //methods
    public void playGame() {
        elizaApp.println(welcome());
        process();
        elizaApp.println(exit());
    }

    public String welcome() {
        return "Welcome, let's play hangman!\n" +
                "Here is the word I am thinking of: ";
    }

    public void process() {
        Game game = new Game();
        String word = game.getWord();
        elizaApp.print(game.getBlanks());
        elizaApp.println("");
        //Scanner keyboard = new Scanner(System.in);

        int i = 1;
        while (i < 7) {
            elizaApp.print("\nEnter your guess or $ for Lifeline: ");
            game.setGuess(elizaApp.input(elizaApp.getKeyboard().nextLine()));

            if (word.contains(game.getGuess()) || game.getGuess().equals("$")) {
                elizaApp.println(game.playGame());
                if (game.getBuilder().indexOf("-") == -1) {
                    elizaApp.println(game.wonGame());
                    break;
                }
            } else {
                elizaApp.println("You have guessed incorrectly " + i + "/6 times.");
                elizaApp.println(game.getStatus());
                if (i == 6) {
                    elizaApp.println(game.looseGame());
                }
                i++;
            }
        }
    }

    public String exit() {
        return "\nThank you for playing!";
    }
}
