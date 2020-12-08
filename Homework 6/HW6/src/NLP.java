import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class NLP
{
    public Word_Map wmap;

    /*You should not use File_Map class in this file since only word hash map is aware of it.
    In fact, you can define the File_Map class as a nested class in Word_Map,
     but for easy evaluation we defined it separately.
     If you need to access the File_Map instances, write wrapper methods in Word_Map class.
    * */

    /*Reads the dataset from the given dir and created a word map */
    public void readDataset(String dir) throws NullPointerException {
        File directory = new File(dir);
        File[] files = directory.listFiles();
        if(null == files) throw new NullPointerException("NullPointer");
        wmap = new Word_Map();
        for(File file : files) {
            if(!file.isFile()) continue;
            enterFile(file);
        }
    }


    /*Finds all the bigrams starting with the given word*/
    public List<String> bigrams(String word) throws NoSuchElementException {
        Set<String> bigramSet = new HashSet<>();
        File_Map fileMap = wmap.get(word);
        if(null == fileMap) throw new NoSuchElementException("NoSuchElement");
        Set<String> allWords = wmap.keySet();
        Set<String> allFiles = fileMap.keySet();
        for(String key : allWords) {
            File_Map secondMap = wmap.get(key);
            for(String fileName : allFiles) {
                if(secondMap.containsKey(fileName)) {
                    List<Integer> firstPositions = (List<Integer>)fileMap.get(fileName);
                    List<Integer> secondPositions = (List<Integer>)secondMap.get(fileName);
                    for(Integer wordPosition : firstPositions) {
                        if(secondPositions.contains(wordPosition + 1)) {
                            bigramSet.add(word + " " + key);
                        }
                    }
                }
            }
        }
        return new ArrayList<String>(bigramSet);
    }


    /*Calculates the tfIDF value of the given word for the given file */
    public float tfIDF(String word, String fileName) {
        double TF = tf(word, fileName);
        double IDF = idf(word);
        return (float)(TF * IDF);
    }


    /*Print the WordMap by using its iterator*/
    public  void printWordMap() {
        Iterator it = wmap.iterator();
        while(it.hasNext()) {
            String word = (String)it.next();
            System.out.print(word + " ");
        }
        System.out.println();
    }

    private void enterFile(File file) {
        int counter = 0;
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while(null != (line = bufferedReader.readLine())) {
                String[] words = line.split(" ");
                for(String word : words) {
                    String filtered = filterWord(word);
                    if(!filtered.isEmpty())
                        wmap.putEntry(filterWord(word), file.getName(), counter++);
                }
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    private String filterWord(String str) {
        String word = "";
        for(char c : str.toCharArray()) {
            if(('A' <= c && c <= 'Z')
            || ('a' <= c && c <= 'z')
            || ('0' <= c && c <= '9')) {
                word += c;
            }
        }
        return word;
    }

    private double tf(String word, String fileName) {
        int termInDocN = wmap.getEntry(word, fileName).size();
        int totalTermsInDocN = 0;
        Set<String> allWords = wmap.keySet();
        for(String key : allWords) {
            File_Map secondMap = wmap.get(key);
            if(secondMap.containsKey(fileName)) {
                totalTermsInDocN += ((List<Integer>)secondMap.get(fileName)).size();
            }
        }
        return termInDocN / (double)totalTermsInDocN;
    }

    private double idf(String word) {
        Set<String> documents = new HashSet<String>();
        Set<String> allWords = wmap.keySet();
        for(String key : allWords) {
            Set<String> files = wmap.get(key).keySet();
            documents.addAll(files);
        }
        int docsWithWordN = wmap.get(word).size();
        return Math.log(documents.size() / (double)docsWithWordN);
    }
}
