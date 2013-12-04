/**
 * 
 */
package com.thecompanybook.contacts;

import java.util.Iterator;
import java.util.SortedMap;

import com.thecompanybook.contacts.extract.ExtractedName;

/**
 * @author fredrik.jorgensen@meltwater.com
 * 
 */
public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        ContactFinder contactFinder = new ContactFinder();
        SortedMap<String, ExtractedName> sortedNames = contactFinder
                .findContactsInTestUrls();
        Iterator itr = sortedNames.entrySet().iterator();

        while (itr.hasNext()) {
            System.out.println(itr.next());
        }

        for (String name : sortedNames.keySet()) {
            System.out.println("RESULT: " + sortedNames.get(name).getScore()
                    + "\t:\t" + name);
        }
    }
}
