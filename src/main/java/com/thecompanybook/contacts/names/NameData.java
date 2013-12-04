/**
 * 
 */
package com.thecompanybook.contacts.names;

/**
 * Class for storing data from name files, collected from
 * http://www.census.gov/genealogy/www/data/1990surnames/names_files.html
 * 
 * @author fredrik.jorgensen@meltwater.com
 * 
 */
public class NameData {
    public enum Type {
        MALE, FEMALE, LAST
    }

    private final String name;
    private final double frequency;
    private final int rank;
    private final Type type;

    /**
     * @param name
     * @param frequency
     * @param rank
     */
    public NameData(String name, double frequency, int rank, Type type) {
        super();
        this.name = name.toLowerCase();
        this.frequency = frequency;
        this.rank = rank;
        this.type = type;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the frequency
     */
    public double getFrequency() {
        return frequency;
    }

    /**
     * @return the rank
     */
    public int getRank() {
        return rank;
    }

    /**
     * @return
     */
    public Type getType() {
        return type;
    }

}
