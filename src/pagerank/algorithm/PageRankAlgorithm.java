/*
 * PageRankAlgorithm.java
 *
 * Created on 26. November 2006, 14:17
 *
 */

package pagerank.algorithm;

import java.util.Vector;

/**
 * - for initializing the DataStructure as needed
 * - for calculating single steps of the PageRank
 */
public class PageRankAlgorithm {
  private  DataStructure daten;
  private  double[][] matrixT;
  private  double daempfungsfaktorD;
  private  double e; // e ist E
  
  /**
     * Creates a new instance of PageRankAlgorithm
     * @param daten the input
     */
  public PageRankAlgorithm(DataStructure daten) {
     this.daten = daten;
     matrixT = new double[daten.getTotalNumberOfPages()][daten.getTotalNumberOfPages()];
     e = .15 / daten.getTotalNumberOfPages();
     changeLinksToIDs();
     setInitialPageRank();
     calculateT();
  }

    /**
     * Changing the initial PageRank to .15/total # of pages
     */
  public void setInitialPageRank() {
    double initalPageRank = .15 / daten.getTotalNumberOfPages();
    DataStructure aktuellerDatensatz = daten;
    for (int i = 0;  aktuellerDatensatz != null && i < daten.getTotalNumberOfPages(); i++) {
        aktuellerDatensatz.setPageRank(initalPageRank);
        aktuellerDatensatz = aktuellerDatensatz.getNext();
    }
  }
  
    /**
     * iterating through the whole DataStructure, changing linkURLs to IDs (if present)
     */
  public void changeLinksToIDs() {
    // "macht aus String-Links IDs"
    DataStructure aktuellerDatensatz = daten;
    for (int i = 0;  aktuellerDatensatz != null && i < daten.getTotalNumberOfPages(); i++) {
      for (int j = 0; aktuellerDatensatz != null && j < aktuellerDatensatz.linkURLs.size(); j++) {
        String url = aktuellerDatensatz.linkURLs.get(j); // (java.lang.String)
        // ueber alle vorhandenen Daten:
        int res = daten.getIDfromURL(url);
        if ((res != 999999)&&(aktuellerDatensatz.getID() != res)) {
          aktuellerDatensatz.linkIDs.addElement(res);
        }
      }
      aktuellerDatensatz = aktuellerDatensatz.getNext();
    }
  }
  
    /**
     * Calculates matrix T, which holds the whole link-structure
     */
  public void calculateT() {
    DataStructure aktuellerDatensatz = daten;
    for (int j = 0;  aktuellerDatensatz != null && j < daten.getTotalNumberOfPages(); j++) {
      // pruefe, ob wir uns auf dem j. Datensatz befinden:
      if (aktuellerDatensatz.getID() != j)
        System.out.println("** Fehler in calculateT: falscher Datensatz -> inkonsistente Daten!");
      for (int z = 0; z < aktuellerDatensatz.linkIDs.size(); z++) {
        int i = 0;
        i = aktuellerDatensatz.linkIDs.get(z); // (java.lang.Integer)
        matrixT[j][i] = 1.0 / (double) aktuellerDatensatz.getNumberOfOutgoingLinks();
      }
      aktuellerDatensatz = aktuellerDatensatz.getNext();
    }

    for (int j = 0; j < matrixT.length; j++) {
      for (int i = 0; i < matrixT.length; i++) {
        System.out.print(matrixT[i][j] + " ");
      }
      System.out.print("\n");
    }

  }
  
    /**
     * makes a PageRank-Interation
     * @return delta (represents change in PageRank)
     */
  public  double makeAnIteration() { // mache genau eine Iteration, schreibe das Ergebnis zurueck
    double[] oldPageRank = new double[daten.getTotalNumberOfPages()];
    double[] newPageRank = new double[daten.getTotalNumberOfPages()];
    // hole aktuelles ("altes") PageRank aus den Daten:
    DataStructure aktuellerDatensatz = daten;
    for (int i = 0;  aktuellerDatensatz != null && i < daten.getTotalNumberOfPages(); i++) {
      oldPageRank[i] = aktuellerDatensatz.getPageRank();
      aktuellerDatensatz = aktuellerDatensatz.getNext();
    }
    
    // Berechne T*PR (bzw. R_i+1 = A*R_i)
    for (int j = 0; j < daten.getTotalNumberOfPages(); j++) { // Gehe ueber alle Zeilen
      double tMalPageRank = 0;
      for (int i = 0; i < daten.getTotalNumberOfPages(); i++) { // Gehe ueber alle Spalten
        tMalPageRank += matrixT[i][j] * oldPageRank[i];
      }
      newPageRank[j] = tMalPageRank;
    }

    double norm_Ri = 0;
    double norm_Ri1 = 0;
    for (int j = 0; j < oldPageRank.length; j++) {
        norm_Ri += Math.abs(oldPageRank[j]);
        norm_Ri1 += Math.abs(newPageRank[j]);
    }
    daempfungsfaktorD = norm_Ri - norm_Ri1;
    double d = daempfungsfaktorD;
    
    // Berechne R_i+1 <- R_i+1 + d*E
    for (int j = 0; j < oldPageRank.length; j++) {
        newPageRank[j] = newPageRank[j] + daempfungsfaktorD * e;
    }
    
    // Berechne delta <- || R_i+1 - R_i ||
    double delta = 0;
    for (int j = 0; j < oldPageRank.length; j++) {
        delta +=  Math.abs(newPageRank[j] - oldPageRank[j]);
    }
    
    // Schreibe das neue PageRank zurueck:
    aktuellerDatensatz = daten;
    for (int i = 0; aktuellerDatensatz != null && i < daten.getTotalNumberOfPages(); i++) {
    aktuellerDatensatz.setPageRank(newPageRank[i]);
      aktuellerDatensatz = aktuellerDatensatz.getNext();
    }
    
    return delta;
  }
}
