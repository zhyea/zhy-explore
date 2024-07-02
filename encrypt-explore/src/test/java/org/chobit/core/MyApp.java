package org.chobit.core;

import com.jiduauto.isc.sdk.keyuse.AESLibUtil;
import com.jiduauto.isc.sdk.keyuse.CipherMode;
import com.jiduauto.isc.sdk.keyuse.IvUtil;

import java.util.Base64;

public class MyApp {


    public static void main(String[] args) {
        String key = "uShallNotPass";
        String iv = IvUtil.getIvStr(12);
        System.out.println(iv);

        String origen = "黄河之水天上来";
        String encrypt = AESLibUtil.encrypt(CipherMode.GCM, key, iv, "", origen);

        System.out.println(encrypt);

        if (null == encrypt) {
            return;
        }

        String decrypt = AESLibUtil.decrypt(CipherMode.GCM, key, iv, "", encrypt);

        System.out.println(decrypt);
    }

    public static void foo() {
        String encryptKey = "Y1FQVVlYbUU0JRcDC4vglwLL/mJjz120KNlkSeSjxWwNwGPhvd9nmlmpXaTi6obDR4jkNic=Euwqz4bl8/Qg==";
        String s = Base64.getEncoder().encodeToString("18516550000".getBytes());
        String a = AESLibUtil.encrypt(CipherMode.GCM, encryptKey, "aPIfxMxTxTtWOcqB", "", s);
        System.out.println(a);
        System.out.println(a.getBytes().length);
        System.out.println("18516550000".getBytes().length);
        String b = AESLibUtil.decrypt(CipherMode.GCM, encryptKey, "aPIfxMxTxTtWOcqB", "",a);
        System.out.println(new String(Base64.getDecoder().decode(b)));
    }





}
