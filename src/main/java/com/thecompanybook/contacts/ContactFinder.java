/**
 * 
 */
package com.thecompanybook.contacts;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.swing.text.html.HTML.Tag;

import org.jsoup.nodes.Document;

import com.thecompanybook.contacts.dom.DomExtractor;
import com.thecompanybook.contacts.dom.DomTagSelector;
import com.thecompanybook.contacts.extract.ExtractedName;
import com.thecompanybook.contacts.extract.NameExtractor;
import com.thecompanybook.contacts.fetch.DocumentFetcher;
import com.thecompanybook.contacts.fetch.URLReader;
import com.thecompanybook.contacts.score.NameScorer;
import com.thecompanybook.contacts.tools.MapValueSort;

/**
 * This class takes one or more URLs as input, and outputs a SortedMap
 * consisting of the contact names found, with the name String as key and a
 * Double confidence score as value.
 * 
 * 
 * @author fredrik.jorgensen@meltwater.com
 * 
 */
public class ContactFinder {
    private static final boolean SELECT_BEST_DOM_ELEMENTS = true;
    NameExtractor nameExtractor;
    DocumentFetcher documentFetcher;
    DomExtractor domExtractor;
    DomTagSelector domTagSelector;
    NameScorer nameScorer;

    public ContactFinder() {
        nameExtractor = new NameExtractor();
        documentFetcher = new DocumentFetcher();
        domExtractor = new DomExtractor();
        nameScorer = new NameScorer();
        domTagSelector = new DomTagSelector();
    }

    /**
     * Find names in the test URLs found in
     * src/main/resources/data/urls/testUrls.txt
     * 
     * @return a SortedMap consisting of the contact names found, with the name
     *         String as key and a Double confidence score as value.
     */
    public SortedMap<String, ExtractedName> findContactsInTestUrls() {
        URLReader urlReader = new URLReader();
        return findContactsInUrls(urlReader);
    }

    /**
     * Find names in the URL specified
     * 
     * @return a SortedMap consisting of the contact names found, with the name
     *         String as key and a Double confidence score as value.
     */
    public SortedMap<String, ExtractedName> findContactsInUrl(URL url) {
        return findContactsInUrl(url);
    }

    /**
     * Find names in the URL (as String) specified
     * 
     * @return a SortedMap consisting of the contact names found, with the name
     *         String as key and a Double confidence score as value.
     */
    public SortedMap<String, ExtractedName> findContactsInUrlString(
            String urlString) throws MalformedURLException {
        URL url = new URL(urlString);
        return findContactsInUrl(url);
    }

    /**
     * Find names in the URL file specified. The file should be a plain text
     * file, one (well-formed) URL per line.
     * 
     * @return a SortedMap consisting of the contact names found, with the name
     *         String as key and a Double confidence score as value.
     */
    public SortedMap<String, ExtractedName> findContactsInUrlFile(File urlFile)
            throws MalformedURLException {
        return findContactsInUrlFile(urlFile);
    }

    /**
     * @param urlReader
     * @return
     */
    private SortedMap<String, ExtractedName> findContactsInUrls(
            URLReader urlReader) {
        URL url = null;
        TreeMap<String, ExtractedName> names = new TreeMap<String, ExtractedName>();
        while ((url = urlReader.next()) != null) {
            List<ExtractedName> allNames = findNamesInURL(url);
            for (ExtractedName name : allNames) {
                names.put(name.toString(), name);
            }
        }
        SortedMap<String, ExtractedName> sortedNames = MapValueSort
                .getValueSortedMap(names);
        return sortedNames;
    }

    /**
     * @param url
     * @return
     */
    private List<ExtractedName> findNamesInURL(URL url) {
        Document doc = documentFetcher.getDocumentStringFromURL(url);
        List<String> candidates;
        if (SELECT_BEST_DOM_ELEMENTS) {
            List<Tag> bestTags = domTagSelector.selectBestTags(1, doc);
            candidates = domExtractor.getElementContentMapFromTags(doc,
                    bestTags);
        } else {
            candidates = domExtractor.getAllContentElementStrings(doc);

        }
        List<ExtractedName> allNames = findAndScoreNamesAmongCandidates(candidates);
        return allNames;
    }

    /**
     * @param allNames
     * @return
     */
    private Tag findBestTag(Map<Tag, List<ExtractedName>> allNames) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @param candidates
     * @return
     */
    private List<ExtractedName> findAndScoreNamesAmongCandidates(
            List<String> candidates) {
        List<ExtractedName> names = new ArrayList<ExtractedName>();

        for (String s : candidates) {
            List<ExtractedName> result = nameExtractor.extractNames(s);
            for (ExtractedName name : result) {
                double score = nameScorer.score(name);
                if (score > 0) {
                    name.setScore(score);
                    names.add(name);
                }
            }
        }

        return names;
    }
}