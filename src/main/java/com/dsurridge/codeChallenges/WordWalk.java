package com.dsurridge.codeChallenges;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class WordWalk {

  public List<List<String>> findLadder(String start, String end, HashSet<String> dict) {

    // process inputs
    start = start.toLowerCase();
    end = end.toLowerCase();
    dict.add(start);
    dict.add(end);

    // Initialise tree variables
    Map<String, List<String>> wordTreeHierarchy = new HashMap<>();
    Map<String, Integer> wordTreeDistanceMeasure = new HashMap<>();
    wordTreeDistanceMeasure.put(start, 0);
    for (String s : dict) {
      wordTreeHierarchy.put(s, new ArrayList<String>());
    }

    List<List<String>> results = new ArrayList<>();
    breadthFirstSearchTreeBuilder(start, end, wordTreeDistanceMeasure, wordTreeHierarchy, dict);

    ArrayList<String> path = new ArrayList<>();
    backtrackSearchTreeForShortestRoute(start, end, wordTreeDistanceMeasure, wordTreeHierarchy, path, results);

    if (results.size() == 0) {
      System.out.println("There are no results");
    } else {
      System.out.println(results);
    }
    return results;
  }

  private void breadthFirstSearchTreeBuilder(String start, String end,
      Map<String, Integer> wordTreeDistanceMeasure, Map<String, List<String>> wordTreeHierarchy,
      Set<String> dict) {

    // Initialise word processor variables
    Queue<String> wordQueue = new LinkedList<>();
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
  }

  private void backtrackSearchTreeForShortestRoute(String start, String end,
      Map<String, Integer> wordTreeDistanceMeasure, Map<String, List<String>> wordTreeHierarchy,
      ArrayList<String> path, List<List<String>> rst) {
    // Recursive function
    path.add(end);
    if (end.equals(start)) {
      Collections.reverse(path);
      rst.add(new ArrayList<String>(path));
      Collections.reverse(path);
    } else {
      for (String s : wordTreeHierarchy.get(end)) {
        // Start back from all adjacent words of the end target
        // Only check routes that are on a closer distance to the start from the given
        // end word
        if (wordTreeDistanceMeasure.get(end) == wordTreeDistanceMeasure.get(s) + 1) {
          backtrackSearchTreeForShortestRoute(start, s, wordTreeDistanceMeasure, wordTreeHierarchy, path, rst);
        }
      }
    }
    path.remove(path.size() - 1);
  }

  protected ArrayList<String> getAdjacentWords(String word, Set<String> dict) {
    ArrayList<String> list = new ArrayList<>();
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
