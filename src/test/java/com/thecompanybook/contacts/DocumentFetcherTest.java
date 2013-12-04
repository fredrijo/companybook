/**
 * 
 */
package com.thecompanybook.contacts;

import java.net.MalformedURLException;
import java.net.URL;

import junit.framework.Assert;

import org.jsoup.nodes.Document;
import org.junit.Test;

import com.thecompanybook.contacts.fetch.DocumentFetcher;

/**
 * @author fredrik.jorgensen@meltwater.com
 * 
 */
public class DocumentFetcherTest {

    @Test
    public void testURLFetcher() {
        DocumentFetcher fetcher = new DocumentFetcher();
        String urlString = "http://www.dagbladet.no";
        URL url = null;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Document doc = fetcher.getDocumentStringFromURL(url);
        Assert.assertTrue(doc != null);
    }
}
