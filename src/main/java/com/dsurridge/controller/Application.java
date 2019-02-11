package com.dsurridge.controller;

import java.util.HashSet;
import codeChallenges.WordWalk;
import com.dsurridge.codeChallenges.DictionarySingleton;

public class Application {

  public static void main(String[] args) {

    if (args != null && args.length == 3) {
      String fileLocation = args[0];
      String startingWord = args[1];
      String destinationWord = args[2];

      HashSet<String> dictionary;
      dictionary = DictionarySingleton.getDictionary(fileLocation);
      WordWalk wordLadder = new WordWalk();
      wordLadder.findLadder(startingWord, destinationWord, dictionary);
    } else {
      System.out.println("Invalid argument - fileLocation startingWord finishWord");
    }
  }

}