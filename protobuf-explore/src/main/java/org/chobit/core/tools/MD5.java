package org.chobit.core.tools;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import java.math.BigInteger;
import java.security.MessageDigest;

public class MD5 {

    private static final Logger logger = LoggerFactory.getLogger(MD5.class);


    /**
     * 执行MD5加密
     *
     * @param str 原始字符串
     * @return MD5加密后的字符串
     */
    public static String encode(String str) {
        if (null == str) {
            return null;
        }
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            String md5 = new BigInteger(1, md.digest()).toString(16);
            //BigInteger会把0省略掉，需补全至32位
            return fillMd5(md5);
        } catch (Exception e) {
            logger.error("MD5加密错误", e);
        }
        return null;
    }


    private static String fillMd5(String md5) {
        return md5.length() == 32 ? md5 : fillMd5("0" + md5);
    }

    private MD5() {
    }
}
