import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Collection;
import java.util.Scanner;

public class MyHashMap {

    public static void main(String[] args) {
        NLP nlp = new NLP();
        nlp.readDataset("dataset");
        nlp.printWordMap();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter the query file or press enter(ex: input.txt): ");
        String fileName = scanner.nextLine().trim();
        if(fileName.isEmpty()) {
            fileName = "input.txt";
        }
        executeQueryFile(nlp, fileName);
    }

    private static void printWithSize(Collection collection) {
        System.out.print("Size: " + collection.size() + " => ");
        System.out.println(collection.toString());
    }

    private static void executeQueryFile(NLP nlp, String file) {
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while(null != (line = bufferedReader.readLine())) {
                String[] token = line.split(" ");
                switch(token[0]) {
                    case "bigram":
                        printWithSize(nlp.bigrams(token[1]));
                        break;
                    case "tfidf":
                        System.out.println(nlp.tfIDF(token[1], token[2]));
                        break;
                }
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}