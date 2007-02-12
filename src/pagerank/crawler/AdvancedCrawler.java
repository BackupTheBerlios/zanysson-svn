package pagerank.crawler;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Vector;
import pagerank.algorithm.*;

/**
 * Crawls Google search result for one search item and the webpages linked with it. 
 * Returns valid DataStructure.
 */
public class AdvancedCrawler {
    
    /**
     * Creates a new instance of AdvancedCrawler
     */
    public AdvancedCrawler() {
    }
    
    /**
     * Tests the function of AdvancedCrawler (and DataStructure and PageRankAlgorithm)
     * @param args (not used)
     */
    public static void main(String[] args) {
        AdvancedCrawler ac = new AdvancedCrawler();
        DataStructure ds = ac.run("hu berlin");
        PageRankAlgorithm pageRank = new PageRankAlgorithm(ds);
    
        System.out.println("NACH AUFRUF VON PAGERANKALGORITHM:");
        System.out.println(ds.toString());
    
        pageRank.makeAnIteration();
        System.out.println("NACH EINER ITERATION:");
        System.out.println(ds.toString());
    }
    
    /**
     * Runs the crawler.
     * @param suchwort The word you want to search for.
     * @return Valid DataStructure
     */
    public DataStructure run(String suchwort) {
        DataStructure ds = null, actualDS = null;
        boolean ersterAufruf = true;
        String suchergebnis = "";
        
        try {
            suchwort = URLEncoder.encode(suchwort, "UTF-8");
            suchergebnis = HTTPDownloader.get("http://www.google.de/search?q=" + suchwort + "&num=100");/* +
                    HTTPDownloader.get("http://www.google.de/search?q=" + suchwort + "&num=100&start=100") +
                    HTTPDownloader.get("http://www.google.de/search?q=" + suchwort + "&num=100&start=200") +
                    HTTPDownloader.get("http://www.google.de/search?q=" + suchwort + "&num=100&start=300") +
                    HTTPDownloader.get("http://www.google.de/search?q=" + suchwort + "&num=100&start=400") +
                    HTTPDownloader.get("http://www.google.de/search?q=" + suchwort + "&num=100&start=500") +
                    HTTPDownloader.get("http://www.google.de/search?q=" + suchwort + "&num=100&start=600") +
                    HTTPDownloader.get("http://www.google.de/search?q=" + suchwort + "&num=100&start=700") +
                    HTTPDownloader.get("http://www.google.de/search?q=" + suchwort + "&num=100&start=800") +
                    HTTPDownloader.get("http://www.google.de/search?q=" + suchwort + "&num=100&start=900");*/

        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }

        Vector<String> links_suchergebnis = LinkParser.parseHTML(suchergebnis);
        
        for (int i = 0; i < links_suchergebnis.size(); i++) {
            System.out.println("|| -- " + links_suchergebnis.get(i));
            if (ersterAufruf) {
                ds = new DataStructure(links_suchergebnis.get(i), true);
                ds.linkURLs = LinkParser.parseHTML(HTTPDownloader.get(links_suchergebnis.get(i)));
                actualDS = ds;
                ersterAufruf = false;
            } else {
                actualDS = ds.addNewPage(links_suchergebnis.get(i));
                actualDS.linkURLs = LinkParser.parseHTML(HTTPDownloader.get(links_suchergebnis.get(i)));
            }

        }
        return ds;
    }
}
