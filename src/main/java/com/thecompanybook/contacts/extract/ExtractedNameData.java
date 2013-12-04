/**
 * 
 */
package com.thecompanybook.contacts.extract;

import com.thecompanybook.contacts.names.NameData;

/**
 * @author fredrik.jorgensen@meltwater.com
 * 
 */
public class ExtractedNameData extends NameData {
    private String occurrence;

    /**
     * @param name
     * @param frequency
     * @param rank
     */
    public ExtractedNameData(String occurence, NameData nameData) {
        super(nameData.getName(), nameData.getFrequency(), nameData.getRank(),
                nameData.getType());
        this.occurrence = occurence;
    }

    /**
     * @return
     */
    public String getOccurrence() {
        return occurrence;
    }

    /**
     * @return
     */
    public Type type() {
        return super.getType();
    }
}
