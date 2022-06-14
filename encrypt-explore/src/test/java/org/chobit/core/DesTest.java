package org.chobit.core;

import org.junit.Test;

/**
 * @author zhangrui137
 */
public class DesTest {


    @Test
    public void check() {
        String salt = "MayTheForceBeWithYou";
        String str = "1";

        String encrypted = DES.encrypt(str, salt);
        System.out.println(encrypted);

        String src = DES.decrypt(encrypted, salt);
        System.out.println(src);
    }

}
