/*
 * DataStructure.java
 *
 * Created on 26. November 2006, 14:16
 *
 */

package pagerank.algorithm;

import java.io.Serializable;
import java.util.Vector;

/**
 * Holds the (crawled) Data for processing and visualization
 */
public class DataStructure implements Serializable {
  /**eigene ID */
  private int id;
  /** eigene URL */
  private String url; 
  /** aktuelle ID, die an ein neues Objekt vergeben werden kann */
  private static int actualID = 0;
  /** aktueller PageRank, zunaechst 1 */
  private double pageRank;
  /** alle URLs, die auf der Seite existieren */
  public Vector<String> linkURLs;
  /** alle IDs von URLs, die auf der Seite existieren */
  public Vector<Integer> linkIDs;
  /** der Nachfolger (Ende = null) */
  DataStructure next; 
  
  /**
     * Creates a new instance of DataStructure
     * @param url The URL of the first DataStructure-Entry
     * @param resetID whether the static actualID-Counter should be resetted or not
     */
  public DataStructure(String url, boolean resetID)
  {
    
    if(resetID)
    {
      actualID = 0;
    }
    
    id = actualID++;
    this.url = url;
    pageRank = 0;
    next = null;
    linkURLs = new Vector<String>(0);
    linkIDs = new Vector<Integer>(0);
  }
  
  
    /**
     * adds a Link to the current entry
     * @param link the external link (i.e. "http://....")
     */
  public void addLink(String link) {
    linkURLs.addElement(link);
  }
  
    /**
     * adds a linkID to the current entry
     * @param link the id of the corresponding linkURL-entry (if existent)
     */
  public void addLink(int link) {
    linkIDs.addElement(link);
  }
  
    /**
     * getter method for the pagerank of the current entry
     * @return the current pagerank
     */
  public double getPageRank() {
    return pageRank;
  }
  
    /**
     * setter method of the current pagerank
     * @param newPageRank the new pagerank
     */
  public void setPageRank(double newPageRank) {
    pageRank = newPageRank;
  }
  
    /**
     * counts the number of outgoing links which are existent in the whole DataStructure
     * @return the number
     */
  public int getNumberOfOutgoingLinks() {
    return linkIDs.size();
  }
  
    /**
     * getter method for the total pages the DataStructure currently holds
     * @return the number
     */
  public int getTotalNumberOfPages() {
    return actualID;
  }
  
    /**
     * getter method for the ID fo the current entry
     * @return the ID
     */
  public int getID() {
    return id;
  }
  
    /**
     * getter method of the current URL
     * @return the URL
     */
  public String getURL()
  {
    return new String(url);
  }
  
    /**
     * getter method of the next DataStructure-Entry
     * @return the DataStructure
     */
  public DataStructure getNext() {
    return next;
  }
  
    /**
     * adds a new entry at the end of the list
     * @param url the URL of the entry
     * @return the DataStructure-entry added
     */
  public DataStructure addNewPage(String url) {
    if (next == null) { // wir sind am Ende der Liste -> fuege hinzu:
      next = new DataStructure(url, false);
      return next;
    } else { // nicht am Ende -> fuege am naechsten hinzu:
      return next.addNewPage(url);
    }
  }
  
    /**
     * search for a URL in the DataStructure
     * @param url the URL you are searching for
     * @return the ID of the URL, if non-existent: 999999
     */
  public int getIDfromURL(String url) {
    if (this.url.equals(url)) {
      return id;
    } else if (next != null) {
      return next.getIDfromURL(url);
    } else {
      return 999999;
    }
  }
  
  /**
   * makes the DataStructure visible easily
   * @return the result
   */
  public String toString() {
    String res = url + " -> ";
    for (int i = 0; i < linkURLs.size(); i++) 
      res += linkURLs.get(i);
    res += "; " + id + " -> ";
    for (int i = 0; i < linkIDs.size(); i++) 
      res += linkIDs.get(i);
    res += "; " + pageRank + "\n";
    
    if (next == null)
      return res;
    else
      return res + next.toString();
  }
}
