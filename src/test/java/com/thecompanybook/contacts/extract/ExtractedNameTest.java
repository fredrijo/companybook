/**
 * 
 */
package com.thecompanybook.contacts.extract;

import org.junit.Assert;
import org.junit.Test;

import com.thecompanybook.contacts.names.MaleNameList;
import com.thecompanybook.contacts.names.NameData;

/**
 * @author fredrik.jorgensen@meltwater.com
 * 
 */
public class ExtractedNameTest {

    @Test
    public void testJohn() {
        MaleNameList maleNameList = new MaleNameList();
        NameData nameData = maleNameList.get("JOHN");
        Assert.assertTrue(nameData != null);
    }

    @Test
    public void testOccurence() {
        MaleNameList maleNameList = new MaleNameList();
        String input = "JOHN";
        NameData nameData = maleNameList.get(input);
        ExtractedNameData extractedNameData = new ExtractedNameData(input,
                nameData);
        Assert.assertTrue(extractedNameData.getName().equals("john"));
        Assert.assertTrue(extractedNameData.getOccurrence().equals("JOHN"));
    }

    @Test
    public void testAdd() {
        MaleNameList maleNameList = new MaleNameList();
        String firstName = "JOHN";
        NameData firstNameData = maleNameList.get(firstName);
        ExtractedNameData extractedNameData = new ExtractedNameData(firstName,
                firstNameData);
        ExtractedName extractedName = new ExtractedName();
        extractedName.add(extractedNameData);
        Assert.assertTrue(extractedNameData.getName().equals("john"));
        Assert.assertTrue(extractedNameData.getOccurrence().equals("JOHN"));
    }

}
