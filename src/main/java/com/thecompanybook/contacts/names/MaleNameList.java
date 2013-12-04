/**
 * 
 */
package com.thecompanybook.contacts.names;

import com.thecompanybook.contacts.names.NameData.Type;

/**
 * @author fredrik.jorgensen@meltwater.com
 * 
 */
public class MaleNameList extends NameList {
    public MaleNameList() {
        super(MaleNameList.class.getClassLoader().getResourceAsStream(
                "data/names/dist.male.first.txt"), Type.MALE);
    }
}
