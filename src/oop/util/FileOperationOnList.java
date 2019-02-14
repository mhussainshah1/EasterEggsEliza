package oop.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileOperationOnList {
    List<String> document;
    String filename ;

    //constructor
    public FileOperationOnList(){
        document = new ArrayList<String>();
        filename = System.getProperty("user.dir") + File.separatorChar + "history.txt";
    }

    public FileOperationOnList(List<String> document, String filename) {
        this.document = document;
        this.filename = System.getProperty("user.dir") + File.separatorChar + filename;
    }

    //Getter and Setter
    public List<String> getDocument() {
        return document;
    }

    public void setDocument(List<String> document) {
        this.document = document;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    //Read File
    public void readFile() throws IOException {
        File file = new File(filename);
        if (file.exists()) {
            document = readLines(file);
        }
    }

    //Write a File
    public void writeFile() throws FileNotFoundException {
        File file = new File(filename);
        PrintWriter writer = new PrintWriter(file);

        StringBuilder builder = new StringBuilder();
        for (String value : document) {
            builder.append(value);
        }
        //String text = builder.toString();
        writer.println(builder);
        writer.close();
    }

    public List<String> readLines(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        List<String> results = new ArrayList<>();
        String line = reader.readLine();
        while (line != null) {
            results.add(line);
            line = reader.readLine();
        }
        reader.close();
        return results;
    }
}
