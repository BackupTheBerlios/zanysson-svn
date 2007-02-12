package pagerank.crawler;

import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.*;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpMethodParams;

/**
 * For easy HTTP downloading
 */
public class HTTPDownloader {
    
    /**
     * Gets the URL by HTTP. Policy: Greedy. 
     * Ignores all kinds of minor errors - otherwise this would take forever.
     * @param url The URL you want to crawl
     * @return The String of the returned webpage.
     */
    public static String get(String url) {
        String result = "";
        
        HttpClient client = new HttpClient();
        HttpMethod method = new GetMethod(url);

        try {
//            HttpClientParams par = new HttpClientParams();
//            par.setConnectionManagerTimeout(30);
//            client.setParams(par);
            //client.getParams().setConnectionManagerTimeout(30);
            client.getHttpConnectionManager().getParams().setConnectionTimeout(3000);
            client.getHttpConnectionManager().getParams().setSoTimeout(30000);
            int statusCode = client.executeMethod(method);
            byte[] responseBody = method.getResponseBody();
            result = new String(responseBody);
        } catch (HttpException e) {
        } catch (IOException e) {
        } finally {
          method.releaseConnection();
        } 
        return result;
    }
}