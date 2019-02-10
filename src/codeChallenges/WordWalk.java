package codeChallenges;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class WordWalk {

  public static void main(String[] args) {
    HashSet<String> dict = new HashSet<String>();
    dict = DictionarySingleton.getDictionary();
    System.out.println(dict.size());

    LinkedList<String> path1 = findLadder("cat", "dog", dict);
    System.out.println(path1);
    System.out.println(path1.size());
  }

  public static LinkedList<String> findLadder(String start, String end, HashSet<String> dict) {

    // process inouts
    start = start.toLowerCase();
    end = end.toLowerCase();
    dict.add(start);
    dict.add(end);

    // Initialise tree variables
    Map<String, ArrayList<String>> wordTreeHierarchy = new HashMap<String, ArrayList<String>>();
    Map<String, Integer> wordTreedistanceMeasure = new HashMap<String, Integer>();
    wordTreedistanceMeasure.put(start, 0);
    for (String s : dict) {
      wordTreeHierarchy.put(s, new ArrayList<String>());
    }

    // Initialise word processor variables 
    Queue<String> wordQueue = new LinkedList<String>();
    wordQueue.add(start);

    LinkedList<String> endQueue = new LinkedList<>();
    
    endQueue.add(start);

    while (!wordQueue.isEmpty()) {
      String currWord = wordQueue.poll();

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
    return endQueue;
  }

  

}
