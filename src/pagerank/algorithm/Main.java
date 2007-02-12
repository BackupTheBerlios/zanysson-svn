/*
 * Main.java
 *
 * Created on 26. November 2006, 14:15
 *
 */

package pagerank.algorithm;

/**
 * Test class for PageRankAlgorithm with two easy examples of DataStructures
 */
public class Main {
  
  /**
   * @param args unused
   */
  public static void main(String[] args) {
    DataStructure daten = new DataStructure("1", true);
    daten.addLink("5");
    
    DataStructure aktuell = daten;
    aktuell = aktuell.addNewPage("2");
    aktuell.addLink("1");
    aktuell.addLink("7");
    
    aktuell = aktuell.addNewPage("3");
    aktuell.addLink("1");
    aktuell.addLink("4");
    
    aktuell = aktuell.addNewPage("4");
    aktuell.addLink("1");
    aktuell.addLink("3");
    
    aktuell = aktuell.addNewPage("5");
    aktuell.addLink("6");
    
    aktuell = aktuell.addNewPage("6");
    aktuell.addLink("5");
    
    aktuell = aktuell.addNewPage("7");
    aktuell.addLink("1");
    aktuell.addLink("8");
    
    aktuell = aktuell.addNewPage("8");
    
    System.out.println("ROHDATEN:");
    System.out.println(daten.toString());
    PageRankAlgorithm pageRank = new PageRankAlgorithm(daten);
    
    System.out.println("NACH AUFRUF VON PAGERANKALGORITHM:");
    System.out.println(daten.toString());
    
    pageRank.makeAnIteration();
    System.out.println("NACH EINER ITERATION:");
    System.out.println(daten.toString());
    
    pageRank.makeAnIteration();
    System.out.println("NACH ZWEI ITERATIONEN:");
    System.out.println(daten.toString());
    
    /*DataStructure daten = new DataStructure("a");
    daten.addLink("e");
    
    DataStructure aktuell = daten;
    aktuell = aktuell.addNewPage("b");
    aktuell.addLink("e");
    
    aktuell = aktuell.addNewPage("c");
    aktuell.addLink("e");
    
    aktuell = aktuell.addNewPage("d");
    aktuell.addLink("e");
    
    aktuell = aktuell.addNewPage("e");
    
    System.out.println("ROHDATEN:");
    System.out.println(daten.toString());
    PageRankAlgorithm pageRank = new PageRankAlgorithm(daten);

    System.out.println("NACH AUFRUF VON PAGERANKALGORITHM:");
    System.out.println(daten.toString());
    
    pageRank.makeAnIteration();
    System.out.println("NACH EINER ITERATION:");
    System.out.println(daten.toString());
    
    pageRank.makeAnIteration();
    System.out.println("NACH ZWEI ITERATIONEN:");
    System.out.println(daten.toString());*/
  }
  
}
