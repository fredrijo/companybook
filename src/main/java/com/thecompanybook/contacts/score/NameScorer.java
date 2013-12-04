/**
 * 
 */
package com.thecompanybook.contacts.score;

import java.util.regex.Pattern;

import com.thecompanybook.contacts.extract.ExtractedName;
import com.thecompanybook.contacts.extract.ExtractedNameData;

/**
 * Class to score a name.
 * 
 * Parameters are: 1) Frequency of the individual parts of the name 2)
 * Capitalization of the name 3) Length of the name, ideal length is by trial
 * and error set to 2.5 tokens
 * 
 * @author fredrik.jorgensen@meltwater.com
 * 
 */
public class NameScorer {
    private static final Pattern PROPER_CAPITALIZATION = Pattern
            .compile("\\p{Upper}\\p{Lower}+");
    double score;
    ExtractedName name;

    /**
     * @param string
     * @return
     */
    public double score(ExtractedName extractedName) {
        score = 0;
        name = extractedName;
        scoreByFrequency();
        scoreByCapitalization();
        scoreByLength();
        return score;
    }

    /**
     * 
     */
    private void scoreByLength() {
        // Ideal name length is 2.5
        double lengthScore = Math.abs(2.5 - name.size());
        score /= Math.log1p(lengthScore);
    }

    /**
     * 
     */
    private void scoreByCapitalization() {
        if (validCapitalization()) {
            score *= 1.5;
        }
    }

    /**
     * @return
     */
    private boolean validCapitalization() {
        for (ExtractedNameData data : name.getNameDataList()) {
            if (!PROPER_CAPITALIZATION.matcher(data.getOccurrence()).matches()) {
                return false;
            }
        }
        return true;
    }

    /**
     * 
     */
    private void scoreByFrequency() {
        for (ExtractedNameData data : name.getNameDataList()) {
            score += data.getFrequency();
        }
    }
}
