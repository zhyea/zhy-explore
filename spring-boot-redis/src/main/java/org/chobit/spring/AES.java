package org.chobit.spring;

import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.encoders.Hex;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;

public class AES {


    public static String encode2(String value, String secretKey, String initialVector)
            throws Exception {
        Key key = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "AES");
        AlgorithmParameterSpec spec = new IvParameterSpec(initialVector.getBytes(StandardCharsets.UTF_8));

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");

        cipher.init(Cipher.ENCRYPT_MODE, key, spec);
        byte[] encrypted = cipher.doFinal(value.getBytes());

        return Hex.toHexString(encrypted);
    }


    public static String encode(String value, String secretKey, String initialVector) {
        try {
            BufferedBlockCipher cipher = getCipher(secretKey, initialVector);
            byte[] bytes = value.getBytes(StandardCharsets.UTF_8);
            byte[] out = new byte[cipher.getOutputSize(bytes.length)];
            int len = cipher.processBytes(bytes, 0, bytes.length, out, 0);
            len += cipher.doFinal(out, len);

            System.out.println(len);

            byte[] arr = new byte[len];
            System.arraycopy(out, 0, arr, 0, len);

            return Hex.toHexString(arr);
        } catch (Exception e) {
            throw new RuntimeException("Data encryption failed. " + e.getMessage(), e);
        }
    }

    private static BufferedBlockCipher getCipher(String secretKey, String iniVector) {
        try {
            byte[] iv = iniVector.getBytes(StandardCharsets.UTF_8);
            CipherParameters params = new ParametersWithIV(new KeyParameter(secretKey.getBytes(StandardCharsets.UTF_8)), iv);
            BufferedBlockCipher cipher = new BufferedBlockCipher(new CBCBlockCipher(new AESEngine()));
            cipher.init(true, params);
            return cipher;
        } catch (Exception ex) {
            throw new RuntimeException("Cannot intialize Bouncy Castle cipher. " + ex.getMessage(), ex);
        }
    }


    private AES() {
        throw new UnsupportedOperationException("Private constructor, cannot be accessed.");
    }
}
