package oop.game;

import oop.util.Randomize;

public class Game {
    private String guess;
    private String word;
    private StringBuilder builder;
    private DatabaseWord databaseWord;
    private Randomize random;

    //constructor
    public Game(){
        databaseWord = new DatabaseWord();
        builder = new StringBuilder();
        random = new Randomize();

        int index = random.getRandomNumber(0, databaseWord.getWords().size());
        word = databaseWord.getWordAt(index);
    }

    //getter and setters
    public void setGuess(String guess) {
        this.guess = guess;
    }

    public String getGuess(){
        return guess;
    }

    public void setBuilder(StringBuilder builder) {
        this.builder = builder;
    }

    public StringBuilder getBuilder() {
        return builder;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    //methods
    public String playGame() {
        if (guess.equals("$")) {
            int index = builder.indexOf("-");
            guess = Character.toString(word.charAt(index));
        }

        int start = 0, end = 0;
        String string = "";
        while (true){
            start = word.indexOf(guess, end); //inclusive
            if(start == -1){
                break;
            }
            end = start + guess.length(); //exclusive
            builder.replace(start, end, guess);
            string = getStatus();
        }
        return string;
    }

    public String looseGame() {
        return "\nSorry, you have no more guesses left. The word was " + word;
    }

    public String wonGame() {
        return "\nYou've won! The word was " + builder;
    }

    public String getBlanks(){
        for (int i = 0; i < word.length(); i++) {
            builder.append("-");
        }
        return builder.toString();
    }

    public String getStatus() {
        return "Your guess so far:" + builder ;
    }

}
