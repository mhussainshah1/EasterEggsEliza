package oop;

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

    public static List<String> history = new ArrayList<String>();

    public static void main(String[] args) {
        welcome();
        process();
        exit();
    }

    public static void welcome() {
        println("Welcome to Eliza");
        FileOperationOnList fo = new FileOperationOnList(history,"history");
        try{
            fo.readFile();
        } catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("Last time you were talking about " + history.get(8));
    }

    public static void process() {
        Response response = new Response();
        Scanner keyboard = new Scanner(System.in);
        String question = "";
        print("Good day. What is your problem? ");

        while (true) {
            print("Enter your response here or Q to quit: ");
            question = keyboard.nextLine().toLowerCase();
            input(question);
            if((question.equalsIgnoreCase("I am feeling great") | question.equals("q"))){
                break;
            }
            if (response.isOptions(question)) {
                continue;
            } else {
                println(response.getAnswer(question));
            }
        }
    }

    public static void exit() {
        //print out document
        for (String str : history) {
            System.out.print(str);
        }
        FileOperationOnList fo = new FileOperationOnList(history,"history");
        try{
            fo.writeFile();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    //output
    public static void print(String str) {
        System.out.print(str);
        history.add(str);
    }

    public static void println(String str) {
        System.out.println(str);
        history.add(str + "\n");
    }

    //input
    public static void input(String str) {
        history.add(str + "\n");
    }
}