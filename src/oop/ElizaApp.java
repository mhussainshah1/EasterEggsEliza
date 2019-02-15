package oop;

import oop.game.Game;
import oop.util.FileOperationOnList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Now that your personal digital therapist, Eliza, is up and running, it's time to step it up with some Easter Eggs.
 * <p>
 * <p>
 * If you type "pig" Eliza should begin speaking in pig latin (Source: https://en.wikipedia.org/wiki/Pig_Latin)
 * <p>
 * Pig Latin Rules:
 * ----------------
 * If the first letter is a consonant, add "ay" to the end
 * If the first letter is a vowel, add "way" or "tay" to the end
 * Don't worry about the "multiple-letters-that-sounds-like one" rule (ex. str-, ch-, th-, etc.)
 * <p>
 * Additional Features:
 * <p>
 * If you type "caps" Eliza should begin speaking in all caps.
 * If you type "play game" Eliza should allow you to play a game, such as your choose your own adventure game.
 * If you type in "red" Eliza 's text should be displayed in red.
 * <p>
 * At the end of the chat, print out the chat document.
 */

public class ElizaApp {

    public List<String> history;
    Scanner keyboard;

    ElizaApp(){
        keyboard = new Scanner(System.in);
    }

    public static void main(String[] args) {
        ElizaApp app = new ElizaApp();
        System.out.println(app.welcome());
        app.process();
        System.out.println(app.exit());
    }

    public Scanner getKeyboard() {
        return keyboard;
    }

    public String welcome() {
        history = new ArrayList<String>();
        FileOperationOnList fo = new FileOperationOnList(history, "history.txt");
        try {
            fo.readFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        history = fo.getDocument();
        String item = "";
        do {
            int index = 2 + (int) (Math.random()* (history.size()-1)); // from line 3 to second last item
            item = history.get(index);
        } while(item.equals("") || item.equals("$"));

        history.clear();
        return input("Welcome to Eliza\n" +
                "Good day , I remember, Last time you were talking about " + item);

    }

    public void process() {
        Response response = new Response();
        String question = "";
        print("What is your problem? ");

        while (true) {
            print("Enter your response here or Q to quit: ");
            question = input(keyboard.nextLine().toLowerCase());
            if ((question.equalsIgnoreCase("I am feeling great") | question.equals("q"))) {
                break;
            }
            if (question.equalsIgnoreCase("play game")) {
                HangmanApp app = new HangmanApp();
                app.playGame();
                continue;
            }
            if (response.isOptions(question)) {
                continue;
            } else {
                println(response.getAnswer(question));
            }
        }
    }

    //output
    public void print(String str) {
        System.out.print(str);
        history.add(str);
    }

    public void println(String str) {
        System.out.println(str);
        history.add(str + "\n");
    }

    //input
    public String input(String str) {
        history.add(str + "\n");
        return str;
    }

    public String exit() {
        String exit = input("Thank you for using Eliza");
        FileOperationOnList fo = new FileOperationOnList(history, "history.txt");
        try {
            fo.writeFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return exit;
    }

    //play game
    public class HangmanApp {
        Game game;
        HangmanApp(){
            game = new Game();
        }
        public void playGame() {
            println(welcome());
            process();
            println(exit());
        }

        public String welcome() {
            return  "Welcome, let's play hangman!\n"+
                    "Here is the word I am thinking of:" + game.getBlanks() +"\n";
        }

        public void process() {
            while (game.isPlay()) {
                print("Enter your guess or $ for Lifeline: ");
                game.setGuess(input(getKeyboard().nextLine()));
                println(game.getResult());
            }
        }

        public String exit() {
            return "\nThank you for playing!";
        }
    }
}