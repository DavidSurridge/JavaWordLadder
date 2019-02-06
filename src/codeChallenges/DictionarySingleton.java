package codeChallenges;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class DictionarySingleton {
  private static HashSet<String> dict = new HashSet<String>();

  private DictionarySingleton() {

  }

  public static HashSet<String> getDictionary() {
    if (dict.isEmpty()) {
      loadDictionary();
    }
    return dict;
  }

  private static void loadDictionary() {
    File file = new File("words.txt");

    try (FileReader fileReader = new FileReader(file); BufferedReader bufferedReader = new BufferedReader(fileReader)) {

      String line = bufferedReader.readLine();

      while (line != null) {
        dict.add(line);
        line = bufferedReader.readLine();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    HashSet<String> x = getDictionary();
    System.out.println(x.size());

  }
}
