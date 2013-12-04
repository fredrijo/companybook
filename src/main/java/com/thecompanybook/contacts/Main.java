/**
 * 
 */
package com.thecompanybook.contacts;

import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import org.jsoup.nodes.Document;

import com.thecompanybook.contacts.extract.ExtractedName;
import com.thecompanybook.contacts.extract.NameExtractor;
import com.thecompanybook.contacts.fetch.DocumentFetcher;
import com.thecompanybook.contacts.fetch.URLReader;
import com.thecompanybook.contacts.score.NameScorer;
import com.thecompanybook.contacts.tools.MapValueSort;

/**
 * @author fredrik.jorgensen@meltwater.com
 * 
 */
public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        NameExtractor nameExtractor = new NameExtractor();
        URLReader urlReader = new URLReader();
        DocumentFetcher documentFetcher = new DocumentFetcher();
        URL url = null;
        HtmlExtractor extractor = new HtmlExtractor();
        TreeMap<String, ExtractedName> names = new TreeMap<String, ExtractedName>();
        NameScorer nameScorer = new NameScorer();
        while ((url = urlReader.next()) != null) {
            Document doc = documentFetcher.getDocumentStringFromURL(url);
            List<String> tableRowElements = extractor
                    .getInnermostElementContent("tr", doc);
            for (String s : tableRowElements) {
                List<ExtractedName> result = nameExtractor.extractNames(s);
                for (ExtractedName name : result) {
                    double score = nameScorer.score(name);
                    if (score > 0) {
                        name.setScore(score);
                        names.put(name.toString(), name);
                    }
                }
            }

            List<String> divElements = extractor.getInnermostElementContent(
                    "div", doc);
            for (String s : divElements) {
                List<ExtractedName> result = nameExtractor.extractNames(s);
                for (ExtractedName name : result) {
                    double score = nameScorer.score(name);
                    if (score > 0) {
                        name.setScore(score);
                        names.put(name.toString(), name);
                    }
                }

            }
        }
        SortedMap<String, ExtractedName> sortedNames = MapValueSort
                .getValueSortedMap(names);
        Iterator itr = sortedNames.entrySet().iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }

        for (String name : sortedNames.keySet()) {
            System.out.println("RESULT: " + names.get(name).getScore()
                    + "\t:\t" + name);
        }
    }
}
