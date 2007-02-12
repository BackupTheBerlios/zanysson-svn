/*
 * LinkParser.java
 *
 * Created on 16. Januar 2007, 00:53
 *
 */

package pagerank.crawler;

import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parses Links out of a (possibly messy) HTML-String.
 */
public class LinkParser {
    
    /**
     * The parser...
     * @param html The input HTML
     * @return A String-Vector of non-local, no-Google, no-PDF URLs found in the HTML.
     */
    public static Vector<String> parseHTML(String html) {
        Vector<String> result = new Vector<String>(0);
        String link = "";
        
        Pattern preP = Pattern.compile("href=\"?", Pattern.CASE_INSENSITIVE);
        Pattern postP = Pattern.compile("( |\"|>)");
        Pattern linkPattern = Pattern.compile("<a[^>]*href=\"?http://[^(>| )]*\"?[^>]*>", Pattern.CASE_INSENSITIVE); 

        Matcher m = linkPattern.matcher(html);

        while (m.find()) {
            
            CharSequence cs = preP.split((CharSequence)m.group())[1];
            cs = postP.split(cs)[0];
        
            link = (String)cs;
        
            if (link.contains((CharSequence)".pdf")) {}
            else if (link.contains((CharSequence)".PDF")) {}
            else if (link.contains((CharSequence)"google")) {}
            else if (link.contains((CharSequence)"/search?q=cache")) {}
            else {        
                result.addElement(link);
            }
        }    

        return result;
    }
    
}
