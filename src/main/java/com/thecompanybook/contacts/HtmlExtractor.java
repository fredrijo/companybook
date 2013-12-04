/**
 * 
 */
package com.thecompanybook.contacts;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author fredrik.jorgensen@meltwater.com
 * 
 */
public class HtmlExtractor {
    public List<String> getElementContent(String tag, Document doc) {
        Elements tableRows = doc.getElementsByTag(tag);
        return asStringList(tableRows);
    }

    public static final String NBSP_IN_UTF8 = "\u00a0";

    /**
     * @param string
     * @param doc
     * @return
     */
    public List<String> getInnermostElementContent(String tag, Document doc) {
        Elements elements = doc.select(tag);
        Elements innerMostDivs = new Elements();

        for (Element element : elements) {
            if (element.select(">" + tag).isEmpty()) {
                innerMostDivs.add(element);
            }
        }
        return asStringList(elements);
    }

    private List<String> asStringList(Elements elements) {
        List<String> list = new ArrayList<String>();

        for (Element element : elements) {
            list.add(element.text().replaceAll(NBSP_IN_UTF8, " "));
        }
        return list;

    }

}
