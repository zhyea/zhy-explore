package org.chobit.core;

import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.engines.DESedeEngine;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.encoders.Hex;

import java.nio.charset.StandardCharsets;

/**
 * @author zhangrui137
 */
public final class DES {
    

    /**
     * 执行加密
     *
     * @param src       原始字符串
     * @param secretKey 秘钥
     * @return 加密后的字符串
     */
    public static String encrypt(String src, String secretKey) {
        byte[] srcBytes = src.getBytes(StandardCharsets.UTF_8);
        byte[] bytes = handle(srcBytes, secretKey, true);
        return Hex.toHexString(bytes);
    }


    /**
     * 执行解密
     *
     * @param encrypted 加密后的字符串
     * @param secretKey 秘钥
     * @return 源字符串
     */
    public static String decrypt(String encrypted, String secretKey) {
        byte[] encryptedBytes = Hex.decode(encrypted);
        byte[] bytes = handle(encryptedBytes, secretKey, false);
        return new String(bytes);
    }

    private static byte[] handle(byte[] bytes, String secretKey, boolean forEncrypt) {
        try {
            BufferedBlockCipher cipher = getCipher(secretKey, forEncrypt);
            byte[] out = new byte[cipher.getOutputSize(bytes.length)];
            int len = cipher.processBytes(bytes, 0, bytes.length, out, 0);
            len += cipher.doFinal(out, len);
            byte[] arr = new byte[len];
            System.arraycopy(out, 0, arr, 0, len);
            return arr;
        } catch (Exception e) {
            return new byte[]{};
        }
    }


    private static BufferedBlockCipher getCipher(String secretKey, boolean forEncrypt) {
        byte[] bytesKey = MD5.encode(secretKey);
        BufferedBlockCipher cipher = new PaddedBufferedBlockCipher(new DESedeEngine());
        cipher.init(forEncrypt, new KeyParameter(bytesKey));
        return cipher;
    }


}
