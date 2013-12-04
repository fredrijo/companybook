/**
 * 
 */
package com.thecompanybook.contacts.extract;

import java.util.ArrayList;
import java.util.List;

import com.thecompanybook.contacts.names.FemaleNameList;
import com.thecompanybook.contacts.names.LastNameList;
import com.thecompanybook.contacts.names.MaleNameList;
import com.thecompanybook.contacts.names.NameData;

/**
 * @author fredrik.jorgensen@meltwater.com
 * 
 */
public class NameExtractor {
    MaleNameList maleNames;
    FemaleNameList femaleNames;
    LastNameList lastNames;

    public NameExtractor() {
        maleNames = new MaleNameList();
        femaleNames = new FemaleNameList();
        lastNames = new LastNameList();
    }

    /**
     * @param name
     * @return
     */
    public List<ExtractedName> extractNames(String text) {
        String[] tokens = tokenize(text);
        List<ExtractedName> nameList = new ArrayList<ExtractedName>();
        ExtractedName currentName = new ExtractedName();
        for (String token : tokens) {
            NameData maleFreq = maleNames.get(token);
            NameData femaleFreq = femaleNames.get(token);
            NameData lastFreq = lastNames.get(token);
            NameData bestName = getMostFrequent(maleFreq, femaleFreq, lastFreq);
            if (bestName != null) {
                ExtractedNameData extractedNameData = new ExtractedNameData(
                        token, bestName);
                currentName.add(extractedNameData);
            } else {
                nameList.add(currentName);
                currentName = new ExtractedName();
            }
        }
        if (currentName.size() > 0) {
            nameList.add(currentName);
        }
        return nameList;
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

    /**
     * @param name
     * @return
     */
    private String[] tokenize(String name) {
        return name.split("\\s+");
    }
}
