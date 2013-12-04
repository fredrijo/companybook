/**
 * 
 */
package com.thecompanybook.contacts.names;

import com.thecompanybook.contacts.names.NameData.Type;

/**
 * @author fredrik.jorgensen@meltwater.com
 * 
 */
public class FemaleNameList extends NameList {
    public FemaleNameList() {
        super(FemaleNameList.class.getClassLoader().getResourceAsStream(
                "data/names/dist.female.first.txt"), Type.FEMALE);
    }
}
