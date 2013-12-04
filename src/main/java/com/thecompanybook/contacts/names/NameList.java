/**
 * 
 */
package com.thecompanybook.contacts.names;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import com.thecompanybook.contacts.names.NameData.Type;

/**
 * Abstract class for storing and processing the data from the 1990 US Census
 * name lists.
 * 
 * @author fredrik.jorgensen@meltwater.com
 * 
 */
public abstract class NameList {
    Map<String, NameData> data;
    Type type;

    public NameList(InputStream is, Type type) {
        data = new HashMap<String, NameData>();
        this.type = type;
        try {
            readNames(is);
        } catch (IOException e) {
            throw new IllegalArgumentException(
                    "NameList constructor requires a valid input stream as argument",
                    e);
        }
    }

    protected void readNames(InputStream is) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line;
        while ((line = br.readLine()) != null) {
            NameData nameData = getNameDataFromLine(line);
            data.put(nameData.getName().toLowerCase(), nameData);
        }
    }

    /**
     * @param line
     */
    private NameData getNameDataFromLine(String line) {
        String[] fields = line.split("\\s+");
        String name = fields[0];
        double frequency = Double.parseDouble(fields[1]);
        int rank = Integer.parseInt(fields[3]);
        NameData nameData = new NameData(name, frequency, rank, type);
        return nameData;
    }

    /**
     * @return
     */
    public int size() {
        return data.size();
    }

    /**
     * @param token
     * @return
     */
    public NameData get(String token) {
        return this.data.get(token.toLowerCase());
    }
}
