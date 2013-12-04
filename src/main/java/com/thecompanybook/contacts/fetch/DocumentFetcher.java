/**
 * 
 */
package com.thecompanybook.contacts.fetch;

import java.io.IOException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * Helper class to fetch docs from the web
 * 
 * @author fredrik.jorgensen@meltwater.com
 * 
 */
public class DocumentFetcher {

    /**
     * @param url
     */
    public Document getDocumentStringFromURL(URL url) {
        Document doc = null;
        try {
            doc = Jsoup.connect(url.toString()).userAgent("Mozilla")
                    .timeout(6000).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc;
    }

}
