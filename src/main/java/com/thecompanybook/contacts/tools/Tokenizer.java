/**
 * 
 */
package com.thecompanybook.contacts.tools;

/**
 * Very simple tokenizer, just to make sure changes to the tokenization done in
 * several classes are done synchroneously
 * 
 * @author fredrik.jorgensen@meltwater.com
 * 
 */
public class Tokenizer {
    public String[] tokenize(String string) {
        return string.split("\\s+");
    }

}
