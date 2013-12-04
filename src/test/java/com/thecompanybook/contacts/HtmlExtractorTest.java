/**
 * 
 */
package com.thecompanybook.contacts;

import java.net.URL;
import java.util.List;

import junit.framework.Assert;

import org.jsoup.nodes.Document;
import org.junit.Test;

import com.thecompanybook.contacts.dom.DomExtractor;
import com.thecompanybook.contacts.fetch.DocumentFetcher;
import com.thecompanybook.contacts.fetch.URLReader;

/**
 * @author fredrik.jorgensen@meltwater.com
 * 
 */
public class HtmlExtractorTest {

    @Test
    public void test() {
        DocumentFetcher fetcher = new DocumentFetcher();
        URLReader urlReader = new URLReader();
        DomExtractor htmlExtractor = new DomExtractor();
        URL url = null;
        while ((url = urlReader.next()) != null) {

            Document doc = fetcher.getDocumentStringFromURL(url);

            List<String> tableRowElements = htmlExtractor
                    .getElementContentFromTag("body", doc);
            Assert.assertTrue(tableRowElements != null);
            Assert.assertTrue(tableRowElements.size() > 0);

        }
    }
}
