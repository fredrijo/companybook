/**
 * 
 */
package com.thecompanybook.contacts.dom;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.html.HTML;
import javax.swing.text.html.HTML.Tag;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Helper class to select DOM elements from an HTML document.
 * 
 * @author fredrik.jorgensen@meltwater.com
 * 
 */
public class DomExtractor {

    public static final String NBSP_IN_UTF8 = "\u00a0";
    // TODO Make this tag list easier to configure
    public static final HTML.Tag[] CONTENT_TAGS = { Tag.DIV, Tag.SPAN, Tag.TD,
            Tag.P, Tag.LI, Tag.STRONG };

    /**
     * @param string
     * @param doc
     * @return
     */
    public List<String> getElementContentFromTag(String tag, Document doc) {
        Elements elements = doc.select(tag);
        Elements innerMostDivs = new Elements();

        for (Element element : elements) {
            if (element.select(">" + tag).isEmpty()) {
                innerMostDivs.add(element);
            }
        }
        return asStringList(elements);
    }

    public List<String> getAllContentElementStrings(Document doc) {
        List<String> map = new ArrayList<String>();
        for (HTML.Tag tag : CONTENT_TAGS) {
            map.addAll(getElementContentFromTag(tag.toString(), doc));
        }
        return map;
    }

    private List<String> asStringList(Elements elements) {
        List<String> list = new ArrayList<String>();

        for (Element element : elements) {
            list.add(element.text().replaceAll(NBSP_IN_UTF8, " "));
        }
        return list;

    }

    /**
     * @param doc
     * @param bestTags
     */
    public List<String> getElementContentMapFromTags(Document doc,
            List<Tag> tags) {
        List<String> elementStrings = new ArrayList<String>();
        for (Tag tag : tags) {
            elementStrings
                    .addAll(getElementContentFromTag(tag.toString(), doc));
        }
        return elementStrings;
    }

}
