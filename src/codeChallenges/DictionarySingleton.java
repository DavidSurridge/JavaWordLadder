package codeChallenges;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class DictionarySingleton {

  private static DictionarySingleton dictionary = null;
  private HashSet<String> dict = new HashSet<>();

  private DictionarySingleton() {
    loadDictionary();
  }

  public static HashSet<String> getDictionary() {

    if (dictionary == null) {
      dictionary = new DictionarySingleton();
    }

    return new HashSet<String>(dictionary.dict);
  }

  private void loadDictionary() {
    File file = new File("words.txt");

    try (FileReader fileReader = new FileReader(file); BufferedReader bufferedReader = new BufferedReader(fileReader)) {

      String line = bufferedReader.readLine();

      while (line != null) {
        dict.add(line.toLowerCase());
        line = bufferedReader.readLine();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
