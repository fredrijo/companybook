/**
 * 
 */
package com.thecompanybook.contacts.fetch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Helper class to read URLs from a URL list file
 * 
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

    public URLReader(File file) throws FileNotFoundException {
        reader = new BufferedReader(new InputStreamReader(new FileInputStream(
                file)));
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
            e1.printStackTrace();
            return readNextLine();
        }
        return urlString;
    }
}
