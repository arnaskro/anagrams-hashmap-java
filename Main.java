import java.io.*;
import java.util.*;

public class Main {

  public static void main(String[] args) {
    // Start measuring execution time
    long startTime = System.currentTimeMillis();

    // Run the function
    (new Main()).run();

    // Calculate the executing time
    long timeDifference = System.currentTimeMillis() - startTime;
    System.out.println("Time: " + timeDifference + " ms");
  }

  public void run() {
    // The name of the file to open.
    String fileName = "eventyr.txt";

    // Read the file and find anagrams
    HashMap<String, List<String>> mappedList = readFileAndFindAnagrams(fileName);

    // Display the anagrams
    for (Map.Entry<String, List<String>> entry: mappedList.entrySet()) {
      // Get all values
      List<String> value = entry.getValue();
      // If the value is not null & the list has more than two anagrams
      if (value != null && value.size() >= 2) {
        for (String wordInTheList: value)
          System.out.print(wordInTheList + " ");

        System.out.println();
      }
    }
  }

  // Read the words from the list
  public HashMap<String, List<String>> readFileAndFindAnagrams(String fileName) {
    // Initiate a list to store anagrams
    HashMap<String, List<String>> mappedList = new HashMap<String, List<String>>();

    // This will reference one line at a time
    String line;

    try {
      // FileReader reads text files in the default encoding
      // Always wrap FileReader in BufferedReader.
      BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));

      while ((line = bufferedReader.readLine()) != null)
        if (line.length() > 0) // Skip empty lines
        {
          // Get the sorted by char version of the word
          String key = getSortedWord(line);

          // If the hashmap doesn't contain the key then add a new entry
          if (!mappedList.containsKey(key)) { // Map first anagrams if the key doesnt exist yet
            List<String> list = new ArrayList<String>();
            list.add(line);
            mappedList.put(key, list);
          } else { // Update the related list of anagrams
            List<String> relatedListOfAnagrams = mappedList.get(key);
            relatedListOfAnagrams.add(line);
            mappedList.put(key, relatedListOfAnagrams);
          }
        }

      // Close file
      bufferedReader.close();
    } catch (FileNotFoundException ex) {
      System.out.println("Unable to open file '" + fileName + "'");
    } catch (IOException ex) {
      System.out.println("Error reading file '" + fileName + "'");
    }

    return mappedList;
  }

  // Get the sorted char version of a word converted to a string
  public String getSortedWord(String word) {
    // Convert the word to a char array
    char[] wordCharArray = word.toCharArray();
    // Sort the char array
    Arrays.sort(wordCharArray);
    // Create a new word from the char array
    return new String(wordCharArray);
  }

}