/**
 * 
 */
package com.thecompanybook.contacts.dom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.text.html.HTML.Tag;

import org.jsoup.nodes.Document;

import com.thecompanybook.contacts.extract.ExtractedName;
import com.thecompanybook.contacts.extract.NameExtractor;
import com.thecompanybook.contacts.score.NameScorer;
import com.thecompanybook.contacts.tools.Tokenizer;

/**
 * This class tries to find the best DOM element type in order to search for
 * names. The assumption is that contacts are uniformly put into one type of DOM
 * elements (e.g., TD, DIV, LI etc).
 * 
 * The DomTagSelector searches for the average density of names in every
 * element, and tries to find the best element tag
 * 
 * TODO As the same HTML tags are found several places in a page, we want to
 * create a more detailed element "signature", e.g. by creating a signature from
 * adding parent nodes, class info, etc., in order to pick a smaller sub-set of
 * the relevant DOM elements
 * 
 * @author fredrik.jorgensen@meltwater.com
 * 
 */
public class DomTagSelector {
    private static final double PUNISHMENT = 0.001;
    private DomExtractor domExtractor = new DomExtractor();
    private NameExtractor nameExtractor = new NameExtractor();
    private Tokenizer tokenizer = new Tokenizer();
    private NameScorer nameScorer = new NameScorer();

    public List<Tag> selectBestTags(int numberOfTags, Document doc) {
        Map<Tag, Double> scores = new HashMap<Tag, Double>();
        for (Tag tag : DomExtractor.CONTENT_TAGS) {
            List<String> elements = domExtractor.getElementContentFromTag(
                    tag.toString(), doc);
            double score = getScore(elements);
            scores.put(tag, new Double(score));

        }
        List<Tag> bestTags = new ArrayList<Tag>();
        for (int i = 0; i < numberOfTags; i++) {
            Tag bestTag = null;
            double bestScore = Double.NEGATIVE_INFINITY;
            for (Entry entry : scores.entrySet()) {
                if ((Double) entry.getValue() > bestScore) {
                    bestTag = (Tag) entry.getKey();
                }
            }
            bestTags.add(bestTag);
            scores.remove(bestTag);
        }
        return bestTags;
    }

    /**
     * @param elements
     * @return
     */
    private double getScore(List<String> elements) {
        double score = 0;
        int tokenCount = 0;
        for (String element : elements) {
            String[] tokens = tokenizer.tokenize(element);
            for (String token : tokens) {
                List<ExtractedName> extractedNames = nameExtractor
                        .extractNames(token);
                if (extractedNames.size() == 0) {
                    score -= PUNISHMENT;
                } else {
                    score += nameScorer.score(extractedNames.get(0));
                }
            }
            tokenCount += tokens.length;
        }
        return score / tokenCount;
    }

    /**
     * @param token
     * @return
     */
    private ExtractedName createExtractedNameFromString(String token) {

        return null;
    }
}
