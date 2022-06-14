package org.chobit.core;

import org.bouncycastle.crypto.digests.MD5Digest;

import java.nio.charset.StandardCharsets;

/**
 * @author zhangrui137
 */
public final class MD5 {


    public static byte[] encode(String str) {
        byte[] strBytes = str.getBytes(StandardCharsets.UTF_8);
        MD5Digest digest = new MD5Digest();
        digest.update(strBytes, 0, strBytes.length);
        byte[] bytes = new byte[digest.getDigestSize()];
        digest.doFinal(bytes, 0);
        return bytes;
    }


}
