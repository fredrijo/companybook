/**
 * 
 */
package com.thecompanybook.contacts.score;

import java.util.List;

import org.junit.Test;

import com.thecompanybook.contacts.extract.ExtractedName;
import com.thecompanybook.contacts.extract.NameExtractor;

/**
 * @author fredrik.jorgensen@meltwater.com
 * 
 */
public class NameScorerTest {

    @Test
    public void test() {
        NameScorer nameScorer = new NameScorer();
    }

    @Test
    public void testScorerReturnsDouble() {
        NameExtractor nameExtractor = new NameExtractor();
        String name = "John Smith";
        // Should return a ExtractedName object
        List<ExtractedName> extractedNames = nameExtractor.extractNames(name);
        NameScorer nameScorer = new NameScorer();
        for (ExtractedName extractedName : extractedNames) {
            double score = nameScorer.score(extractedName);
            System.out.println(score + " : " + extractedName.toString());
        }
    }
}
