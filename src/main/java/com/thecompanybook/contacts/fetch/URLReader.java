/**
 * 
 */
package com.thecompanybook.contacts.fetch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author fredrik.jorgensen@meltwater.com
 * 
 */
public class URLReader {

    BufferedReader reader;

    public URLReader() {
        reader = new BufferedReader(
                new InputStreamReader(URLReader.class.getClassLoader()
                        .getResourceAsStream("data/urls/testUrls.txt")));
    }

    public URL next() {
        String urlString = readNextLine();
        URL url = null;
        if (urlString == null)
            return null;

        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    /**
     * @return
     */
    private String readNextLine() {
        String urlString = null;
        try {
            urlString = reader.readLine();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return urlString;
    }
}
