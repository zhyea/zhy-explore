package org.chobit.core;

import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.encoders.Hex;

import java.nio.charset.StandardCharsets;

public final class AES {


    private static final String IV = "87ad2a64d29dfa64885e3c57d00115d1";


    /**
     * 执行加密
     *
     * @param src  原始字符串
     * @param salt 加密盐
     * @return 加密后的字符串
     */
    public static String encrypt(String src, String salt) {
        byte[] srcBytes = src.getBytes(StandardCharsets.UTF_8);
        byte[] bytes = handle(srcBytes, salt, true);
        return Hex.toHexString(bytes);
    }


    /**
     * 执行解密
     *
     * @param encrypted 加密后的字符串
     * @param salt      加密盐
     * @return 源字符串
     */
    public static String decrypt(String encrypted, String salt) {
        byte[] encryptedBytes = Hex.decode(encrypted);
        byte[] bytes = handle(encryptedBytes, salt, false);
        return new String(bytes);
    }


    private static byte[] handle(byte[] bytes, String salt, boolean forEncrypt) {
        try {
            BufferedBlockCipher cipher = getCipher(salt, forEncrypt);
            byte[] out = new byte[cipher.getOutputSize(bytes.length)];
            int len = cipher.processBytes(bytes, 0, bytes.length, out, 0);
            len += cipher.doFinal(out, len);
            byte[] arr = new byte[len];
            System.arraycopy(out, 0, arr, 0, len);
            return arr;
        } catch (Exception e) {
            e.printStackTrace();
            return new byte[]{};
        }
    }


    private static BufferedBlockCipher getCipher(String secretKey, boolean forEncrypt) {
        byte[] iv = IV.substring(IV.length() - 16).getBytes(StandardCharsets.UTF_8);
        int keyLen = iv.length;
        if (keyLen < 16 || keyLen > 32 || (keyLen & 7) != 0){
            System.out.println("------------------------------------");
        }
        CipherParameters params = new ParametersWithIV(new KeyParameter(secretKey.getBytes(StandardCharsets.UTF_8)), iv);
        BufferedBlockCipher cipher = new BufferedBlockCipher(new CBCBlockCipher(new AESEngine()));
        cipher.init(forEncrypt, params);
        return cipher;
    }

    private AES() {
    }
}
