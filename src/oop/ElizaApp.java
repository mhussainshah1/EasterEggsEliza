package oop;

import oop.game.Game;
import oop.game.HangmanApp;
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

    public static void main(String[] args) {
        ElizaApp app = new ElizaApp();
        System.out.println(app.welcome());
        app.process();
        System.out.println(app.exit());
    }

    public String welcome() {
        history = new ArrayList<String>();
        FileOperationOnList fo = new FileOperationOnList(history,"history");
        try{
            fo.readFile();
        } catch (IOException e){
            e.printStackTrace();
        }
        history = fo.getDocument();
        int index = (int)(Math.random() * history.size());
        String item = history.get(index);
        history.clear();
        return input("Welcome to Eliza\n"+
                        "Good day , I remember, Last time you were talking about " + item);

    }

    public void process() {
        Response response = new Response();
        Scanner keyboard = new Scanner(System.in);
        String question = "";
        print("What is your problem? ");

        while (true) {
            print("Enter your response here or Q to quit: ");
            question =  input(keyboard.nextLine().toLowerCase());
            if((question.equalsIgnoreCase("I am feeling great") | question.equals("q"))){
                break;
            }
            if (question.equalsIgnoreCase("play game")) {
                HangmanApp game = new HangmanApp();
                println(game.welcome());
                processGame();
                println(game.exit());
                continue;
            }
            if (response.isOptions(question)) {
                continue;
            } else {
                println(response.getAnswer(question));
            }
        }
    }

    public String exit() {
        String exit =  input("Thank you for using Eliza");
        FileOperationOnList fo = new FileOperationOnList(history,"history");
        try{
            fo.writeFile();
        } catch (IOException e){
            e.printStackTrace();
        }
        return exit;
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
        history.add(str+ "\n");
        return str;
    }

    //play game
    public void processGame() {
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
}