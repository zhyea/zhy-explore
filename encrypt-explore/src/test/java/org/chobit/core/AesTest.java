package org.chobit.core;


import org.junit.Test;

public class AesTest {


    @Test
    public void check() {
        String salt = "MayTheForceBeWithYou";
        String str = "1";

        String encrypted = AES.encrypt(str, salt);
        System.out.println(encrypted);

        String src = AES.decrypt(encrypted, salt);
        System.out.println(src);
    }


}
