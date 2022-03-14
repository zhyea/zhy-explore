package org.chobit.core;

import org.chobit.core.tools.ToolKit;
import org.junit.Test;

/**
 * @author zhangrui137
 */
public class ToolKitTest {


    @Test
    public void uuid() {
        String uuid = ToolKit.uuid();
        System.out.println(uuid);
    }


}
