package controller;

import java.util.HashSet;
import codeChallenges.DictionarySingleton;
import codeChallenges.WordWalk;

public class Application {

  public static void main(String[] args) {
    HashSet<String> dict = new HashSet<String>();
    dict = DictionarySingleton.getDictionary();
    WordWalk wordLadder = new WordWalk();
    wordLadder.findLadder("Oecanthus", "oecodomic", dict);
    wordLadder.findLadder("cat", "dog", dict);
    wordLadder.findLadder("Total", "Goats", dict);
  }

}
