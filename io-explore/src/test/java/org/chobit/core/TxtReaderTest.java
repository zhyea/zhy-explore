package org.chobit.core;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author robin
 */
public class TxtReaderTest {

    @Test
    public void read() {
        String path = "/zhy/tmp/waitig.php";
        String s = TxtReader.read(path);
        System.out.println(s);
        Assert.assertNotEquals("", s);
    }

}
