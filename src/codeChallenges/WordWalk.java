package codeChallenges;

import java.util.HashSet;
import java.util.LinkedList;

public class WordWalk {

  public static void main(String[] args) {
    HashSet<String> dict = new HashSet<String>();
    dict = DictionarySingleton.getDictionary();
    searchPath("cat", "dog", dict);

    /*
     * dict.add("cat".toLowerCase()); dict.add("cag".toLowerCase());
     * dict.add("cog".toLowerCase()); dict.add("dog".toLowerCase());
     * searchPath("cat".toLowerCase(), "dog".toLowerCase(), dict);
     */
  }

  public static void searchPath(String start, String end, HashSet<String> dict) {
    start = start.toLowerCase();
    end = end.toLowerCase();
    LinkedList<String> wordQueue = new LinkedList<>();
    LinkedList<String> endQueue = new LinkedList<>();
    wordQueue.add(start);
    endQueue.add(start);
    dict.remove(start);

    while (!wordQueue.isEmpty()) {
      String currWord = wordQueue.pop();

      for (int i = 0; i < currWord.length(); i++) {
        char[] currCharArr = currWord.toCharArray();
        for (char c = 'a'; c <= 'z'; c++) {
          currCharArr[i] = c;

          String newWord = new String(currCharArr);
          if (dict.contains(newWord)) {
            wordQueue.add(newWord);
            endQueue.add(newWord);
            dict.remove(newWord);
          }
        }
      }
    }
    System.out.println(endQueue);
  }

}
