
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import codeChallenges.WordWalk;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AppTest extends TestCase {

  protected HashSet<String> dict = new HashSet<>();
  protected WordWalk search = new WordWalk();
  protected List<List<String>> results;

  public static Test suite() {
    return new TestSuite(AppTest.class);
  }

  protected void setUp() {

    dict.add("toon");
    dict.add("poon");
    dict.add("plee");
    dict.add("same");
    dict.add("poie");
    dict.add("plea");
    dict.add("plie");
    dict.add("poin");

    results = search.findLadder("toon", "plea", dict);
  }

  public void testStartExampleSizeCheck() {
    assertTrue(results.size() == 1);
  }

  public void testStartExampleFirstWordCheck() {
    assertTrue("toon".equals(results.get(0).get(0)));
  }

  public void testStartExampleLastWordCheck() {

    assertTrue("plea".equals(results.get(0).get(6)));
  }

  public void testNoLadder() {
    dict.add("cat");
    dict.add("cog");
    dict.add("dog");
    results = search.findLadder("cat", "dog", dict);
    assertTrue(results.size() == 0);
  }

}
