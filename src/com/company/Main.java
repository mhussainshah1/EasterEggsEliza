package com.company;

import java.util.HashMap;
import java.util.Map;
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
 * At the end of the chat, print out the chat history.
 */
public class Main {

    public static void main(String[] args) {
        String[] hedges = {
                "Please tell me more ",
                "Many of my patients tell me the same thing ",
                "It is getting late, maybe we had better quit "
        };

        String[] qualifiers = {
                "Why do you say that ",
                "You seem to think that ",
                "So, you are concerned that "
        };

        Map<String, String> replace = new HashMap<>();
        replace.put("i", "you");
        replace.put("me", "you");
        replace.put("my", "your");
        replace.put("am", "are");

        Scanner keyboard = new Scanner(System.in);
        String question, answer = "";
        boolean isPig = false, isCap = false;

        System.out.print("Good day. What is your problem? ");

        while (true) {
            System.out.print("Enter your response here or Q to quit: ");
            question = keyboard.nextLine().toLowerCase();

            if (question.equalsIgnoreCase("pig")) {
                isPig = isPig(isPig);
                continue;
            }

            if (question.equalsIgnoreCase("caps")) {
                isCap = isCap(isCap);
                continue;
            }

            if (question.equalsIgnoreCase("I am feeling great") | question.equals("q")) {
                break;
            } else {
                int choice = 1 + (int) (Math.random() * 2);
                switch (choice) {
                    case 1:
                        int i = (int) (Math.random() * hedges.length);
                        answer = hedges[i];
                        break;
                    case 2:
                        int j = (int) (Math.random() * qualifiers.length);
                        answer = qualifiers[j] + getReplacedString(replace, question);
                        break;
                    default:
                        System.out.println("Invalid Choice");
                }

                if (isPig) {
                    answer = getPigString(answer);
                }
                if (isCap) {
                    answer = answer.toUpperCase();
                }
                System.out.println(answer);
            }
        }

        // print out history
    }

    public static boolean isPig(boolean check){
        if(check){
            return false;
        } else{
            return true;
        }
    }

    public static boolean isCap(boolean check){
        if(check){
            return false;
        } else {
            return true;
        }
    }

    public static String getReplacedString(Map replace, String question) {
        String replacedString = "";
        for (String retval : question.split(" ")) {
            if (replace.containsKey(retval)) {
                replacedString += replace.get(retval) + " ";
            } else {
                replacedString += retval + " ";
            }
        }
        return replacedString;
    }

    public static String getPigString(String str) {
        String pigString = "";
        for (String retval : str.split(" ")) {
            char v = retval.charAt(0);
            if (v == 'a' || v == 'e' || v == 'i' || v == 'o' || v == 'u') {
                int choice = 1+ (int) (Math.random() * 2) ;
                switch (choice){
                    case 1:
                        pigString += retval + "way ";
                        break;
                    case 2:
                        pigString += retval + "tay ";
                        break;
                }
            } else {
                pigString += retval + "ay ";
            }
        }
        return pigString;
    }
}