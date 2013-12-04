/**
 * 
 */
package com.thecompanybook.contacts.names;

import com.thecompanybook.contacts.names.NameData.Type;

/**
 * @author fredrik.jorgensen@meltwater.com
 * 
 */
public class LastNameList extends NameList {
    public LastNameList() {
        super(LastNameList.class.getClassLoader().getResourceAsStream(
                "data/names/dist.all.last.txt"), Type.LAST);
    }
}
