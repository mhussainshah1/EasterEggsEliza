package help;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ReadListOfBooks {

    public static void main(String[] args) {
        /**
         * create an array list of type string
         * ===================================
         * when you specify the type like you have below (in the <>'s)you are using Generics
         * When you take an element out of a Collection,
         * you must cast it to the type of element that is stored in the collection.
         * Besides being inconvenient, this is unsafe.
         * The compiler does not check that your cast is the same as the collection's type,
         * so the cast can fail at run time.
         * Generics provides a way for you to communicate the type
         * of a collection to the compiler, so that it can be checked.
         * Once the compiler knows the element type of the collection,
         * the compiler can check that you have used the collection
         * consistently and can insert the correct casts on values
         * being taken out of the collection.
         */
        List<String> books = new ArrayList<String>();

        //get a filename
        String filename = (System.getProperty("user.dir") + File.separatorChar + "mybooks.txt");
        System.out.println((System.getProperty("user.dir") + File.separatorChar + "mybooks.txt"));
        System.out.println(Paths.get("c:\\myfolder\\myfile.txt"));

        /**
         *  write file
         *  ==========
         *  Data can be output to a text file using the class PrintWriter
         *  This class includes the methods print and println that you have
         *  already used for terminal output
         *  A PrintWriter is opened like a Scanner
         *  you must handle the FileNotFound Exception
         */
        PrintWriter writer;
        try {
            writer = new PrintWriter(new File(filename));
            for (Integer i = 0; i < 20; i++) {
                writer.println("Book " + i.toString());
            }
            //you must close the PrintWriter
            writer.close();

        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }

        try {
            System.out.println("Read in list of books");
            books = readLines(new File(filename));
            for (String book : books) {
                System.out.println(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //You can see how many items are in your list by using the size method
        System.out.println("\nHow many elements are in your list?" + books.size());

        //add a new book
        books.add("New Book");
        //delete the second book
        books.remove(1);
        //the Collections class contains static methods like sort
        //that work on collections
        Collections.sort(books);

        //print your sorted collection of books
        System.out.println("\n -- sorted books --");
        for (String book : books) {
            System.out.println(book.toString());
        }

        //another way to print a collection
        System.out.println("\nAnother way to print a collection");
        System.out.println(books);

        //replace book 0 with your name
        books.set(0, "Bart Simpson");

        //unsort the list now
        Collections.shuffle(books);
        System.out.println("\n -- unsorted books --");
        for (String book : books) {
            System.out.println(book);
        }

        //get a random book from the arraylist
        Random r = new Random();
        int index = 1 + r.nextInt(books.size() - 1);
        System.out.println("random book:" + books.get(index));
    }

    public static ArrayList<String> readLines(File file) throws Exception {
        if (!file.exists()) {
            return new ArrayList<String>();
        }
        BufferedReader reader = new BufferedReader(new FileReader(file));
        ArrayList<String> results = new ArrayList<String>();
        String line = reader.readLine();
        while (line != null) {
            results.add(line);
            line = reader.readLine();
        }
        reader.close();
        return results;
    }

}
