package oop;

import oop.game.HangmanApp;

import java.util.HashMap;
import java.util.Map;

public class Response {

    private boolean isPig, isCap, isRed;
    private String replacedString;
    private String answer;
    private String[] hedges;
    private String[] qualifiers;
    private Map<String, String> replace;
    private Randomize random;
    private PigLatin pig;

    //Constructor
    public Response() {
        hedges = new String[]{
                "Please tell me more ",
                "Many of my patients tell me the same thing ",
                "It is getting late, maybe we had better quit "
        };

        qualifiers = new String[]{
                "Why do you say that ",
                "You seem to think that ",
                "So, you are concerned that "
        };

        replace = new HashMap<>();
        replace.put("i", "you");
        replace.put("me", "you");
        replace.put("my", "your");
        replace.put("am", "are");

        random = new Randomize();
        pig = new PigLatin();
    }

    //getter and setter
    public String getReplacedString(Map replace, String question) {
        replacedString = "";
        for (String retval : question.split(" ")) {
            if (replace.containsKey(retval)) {
                replacedString += replace.get(retval) + " ";
            } else {
                replacedString += retval + " ";
            }
        }
        return replacedString;
    }

    public String getAnswer(String question) {
        answer = getRandomResponse(question);
        if (isPig) {
            answer = pig.getPigString(answer);
        }
        if (isCap) {
            answer = answer.toUpperCase();
        }
        if (isRed) {
            answer = IColorCode.ANSI_RED_BACKGROUND + answer + IColorCode.ANSI_RESET;
        }
        return answer;
    }

    //methods
    public static boolean onOff(boolean check) {
        return !check;
    }

    public String getRandomResponse(String question) {
        int choice = random.getRandomNumber(1, 2);
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
                answer = "Invalid Choice";
                break;
        }
        return answer;
    }

    public boolean isOptions(String question) {
        switch (question) {
            case "pig":
                isPig = !isPig;
                break;
            case "caps":
                isCap = !isCap;
                break;
            case "red":
                isRed = !isRed;
                break;
            case "play games":
                HangmanApp.main(new String[0]);
                break;
            default:
                return false;
        }
        return true;
    }


}