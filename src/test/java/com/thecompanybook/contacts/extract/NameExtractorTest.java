/**
 * 
 */
package com.thecompanybook.contacts.extract;

import java.util.List;

import org.junit.Test;

/**
 * @author fredrik.jorgensen@meltwater.com
 * 
 */
public class NameExtractorTest {

    @Test
    public void testConstructor() {
        NameExtractor nameExtractor = new NameExtractor();
    }

    @Test
    public void testAddName() {

    }

    @Test
    public void testExtractor() {
        NameExtractor nameExtractor = new NameExtractor();
        String name = "John Smith";
        // Should return a ExtractedName object
        List<ExtractedName> extractedName = nameExtractor.extractNames(name);
        if (extractedName != null)
            System.out.println(extractedName.toString());
    }
}
