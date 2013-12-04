/**
 * 
 */
package com.thecompanybook.contacts.names;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author fredrik.jorgensen@meltwater.com
 * 
 */
public class NameListTest {

    @Test
    public void testLoading() {
        NameList maleNames = new MaleNameList();
        NameList femaleNames = new FemaleNameList();
        NameList lastNames = new LastNameList();
    }

    @Test
    public void testSize() {
        NameList[] lists = { new MaleNameList(), new FemaleNameList(),
                new LastNameList() };
        for (NameList list : lists) {
            Assert.assertTrue(list.size() > 0);
            System.out.println(list.getClass().getSimpleName() + " size="
                    + list.size());
        }
    }

    @Test
    public void testJohn() {
        NameList maleNames = new MaleNameList();
        NameData john = maleNames.get("John");
        Assert.assertTrue(john.getType() == NameData.Type.MALE);
    }

    @Test
    public void testSmith() {
        NameList lastNames = new LastNameList();
        NameData smith = lastNames.get("Smith");
        Assert.assertTrue(smith.getType() == NameData.Type.LAST);
    }

}
