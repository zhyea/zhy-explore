package org.chobit.spring;

import org.junit.Test;

public class AesTest {


    @Test
    public void encode() throws Exception {
        String src = "098f6bcd4621d373cade4e832627b4f6";
        String key = "1234567891234567";
        String iv = "8912345678912345";

        String encrypted = AES.encode(src, key, iv);
        System.out.println(encrypted);
    }

}
