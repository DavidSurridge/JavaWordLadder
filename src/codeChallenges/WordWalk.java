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
    // List<List<String>> noPath = findLadder("Oecanthus", "oecodomic", dict);
    List<List<String>> path1 = findLadder("cat", "dog", dict);
    System.out.println(path1);
    System.out.println(path1.size());
  }

  public static List<List<String>> findLadder(String start, String end, HashSet<String> dict) {

    // process inputs
    start = start.toLowerCase();
    end = end.toLowerCase();
    dict.add(start);
    dict.add(end);

    // Initialise tree variables
    Map<String, ArrayList<String>> wordTreeHierarchy = new HashMap<String, ArrayList<String>>();
    Map<String, Integer> wordTreeDistanceMeasure = new HashMap<String, Integer>();
    wordTreeDistanceMeasure.put(start, 0);
    for (String s : dict) {
      wordTreeHierarchy.put(s, new ArrayList<String>());
    }

    List<List<String>> results = new ArrayList<List<String>>();

    BFS(start, end, wordTreeDistanceMeasure, wordTreeHierarchy, dict);

    return results;
  }

  private static void BFS(String start, String end, Map<String, Integer> wordTreeDistanceMeasure,
      Map<String, ArrayList<String>> wordTreeHierarchy, Set<String> dict) {

    // Initialise word processor variables
    Queue<String> wordQueue = new LinkedList<String>();
    wordQueue.add(start);

    // Build word tree
    while (!wordQueue.isEmpty()) {
      String queueWord = wordQueue.poll();
      List<String> adjacentWords = getAdjacentWords(queueWord, dict);

      for (String adjWord : adjacentWords) {
        wordTreeHierarchy.get(adjWord).add(queueWord);
        if (!wordTreeDistanceMeasure.containsKey(adjWord)) {
          wordTreeDistanceMeasure.put(adjWord, wordTreeDistanceMeasure.get(queueWord) + 1);
          wordQueue.add(adjWord);
        }
      }
    }

    System.out.println(wordTreeDistanceMeasure);

  }

  public static ArrayList<String> getAdjacentWords(String word, Set<String> dict) {
    ArrayList<String> list = new ArrayList<String>();
    for (int i = 0; i < word.length(); i++) {
      char[] currCharArr = word.toCharArray();
      for (char c = 'a'; c <= 'z'; c++) {
        currCharArr[i] = c;

        String newWord = new String(currCharArr);
        if (dict.contains(newWord)) {
          list.add(newWord);
        }
      }
    }

    return list;
  }

}
