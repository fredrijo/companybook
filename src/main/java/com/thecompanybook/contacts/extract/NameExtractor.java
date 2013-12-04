/**
 * 
 */
package com.thecompanybook.contacts.extract;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.thecompanybook.contacts.names.FemaleNameList;
import com.thecompanybook.contacts.names.LastNameList;
import com.thecompanybook.contacts.names.MaleNameList;
import com.thecompanybook.contacts.names.NameData;
import com.thecompanybook.contacts.tools.Tokenizer;

/**
 * Class to extract names from running text.
 * 
 * The class uses 1990 US Census lists from
 * http://www.census.gov/genealogy/www/data/1990surnames/
 * 
 * If a name (disregarding capitalization) matches a name in one the lists, it
 * will be selected as part of a name
 * 
 * @author fredrik.jorgensen@meltwater.com
 * 
 */
public class NameExtractor {
    private final static Pattern INITIAL_PATTERN = Pattern
            .compile("\\p{Upper}\\.?");
    MaleNameList maleNames;
    FemaleNameList femaleNames;
    LastNameList lastNames;
    Tokenizer tokenizer;

    public NameExtractor() {
        maleNames = new MaleNameList();
        femaleNames = new FemaleNameList();
        lastNames = new LastNameList();
        tokenizer = new Tokenizer();
    }

    /**
     * @param name
     * @return
     */
    public List<ExtractedName> extractNames(String text) {
        String[] tokens = tokenizer.tokenize(text);
        List<ExtractedName> nameList = new ArrayList<ExtractedName>();
        ExtractedName currentName = new ExtractedName();
        for (String token : tokens) {
            NameData bestName = getBestName(token);
            if (bestName != null) {
                ExtractedNameData extractedNameData = new ExtractedNameData(
                        token, bestName);
                currentName.add(extractedNameData);
            } else {
                if (currentName.size() > 0) {
                    nameList.add(currentName);
                }
                currentName = new ExtractedName();
            }
        }
        if (currentName.size() > 0) {
            nameList.add(currentName);
        }
        return nameList;
    }

    /**
     * @param token
     * @return
     */
    private NameData getBestName(String token) {
        if (isInitial(token)) {
            return NameData.newInitial(token);
        }
        NameData maleFreq = maleNames.get(token);
        NameData femaleFreq = femaleNames.get(token);
        NameData lastFreq = lastNames.get(token);
        NameData bestName = getMostFrequent(maleFreq, femaleFreq, lastFreq);
        return bestName;
    }

    /**
     * @param token
     * @return
     */
    private boolean isInitial(String token) {
        return INITIAL_PATTERN.matcher(token).matches();
    }

    /**
     * @param maleFreq
     * @param femaleFreq
     * @param lastFreq
     * @return
     */
    private NameData getMostFrequent(NameData... names) {
        NameData mostFrequentName = null;
        for (NameData name : names) {
            if (mostFrequentName == null) {
                mostFrequentName = name;
            } else if (name != null && mostFrequentName != null
                    && name.getFrequency() > mostFrequentName.getFrequency()) {
                mostFrequentName = name;
            }
        }
        return mostFrequentName;
    }

}
