/**
 * 
 */
package com.thecompanybook.contacts.extract;

import java.util.ArrayList;
import java.util.List;

/**
 * Data container for an extracted name
 * 
 * @author fredrik.jorgensen@meltwater.com
 * 
 */
public class ExtractedName {
    List<ExtractedNameData> nameList;
    double accumulatedFrequency = 0;
    private double score;

    /**
     * 
     */
    public ExtractedName() {
        super();
        nameList = new ArrayList<ExtractedNameData>();
    }

    /**
     * @param extractedNameData
     */
    public void add(ExtractedNameData extractedNameData) {
        nameList.add(extractedNameData);
        accumulatedFrequency += extractedNameData.getFrequency();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (ExtractedNameData nameData : nameList) {
            sb.append(nameData.getOccurrence()).append("/")
                    .append(nameData.type()).append(" ");
        }
        return sb.toString().trim();
    }

    /**
     * @return
     */
    public int size() {
        return nameList.size();
    }

    /**
     * @return
     */
    public List<ExtractedNameData> getNameDataList() {
        return nameList;
    }

    /**
     * @param score
     */
    public void setScore(double score) {
        this.score = score;
    }

    /**
     * @return the score
     */
    public double getScore() {
        return score;
    }
}
