/**
 * 
 */
package com.thecompanybook.contacts;

import java.net.URL;
import java.util.List;

import org.jsoup.nodes.Document;
import org.junit.Test;

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
        HtmlExtractor htmlExtractor = new HtmlExtractor();
        URL url = null;
        while ((url = urlReader.next()) != null) {

            Document doc = fetcher.getDocumentStringFromURL(url);

            List<String> tableRowElements = htmlExtractor.getElementContent("tr",
                    doc);
            for (String s : tableRowElements) {
                System.out.println("TR: " + s);
            }

            List<String> divElements = htmlExtractor.getInnermostElementContent(
                    "div", doc);
            for (String s : divElements) {
                System.out.println("DIV: " + s);
            }

        }
    }
}
