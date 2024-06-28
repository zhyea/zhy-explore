package org.chobit.mvel;

import java.util.Date;

/**
 * mvel 函数定义
 *
 * @author rui.zhang
 */
public class MvelFunctions {


    public String subStr(String s, Integer idx) {
        return s.substring(idx);
    }


    public String today() {
        return new Date().toString();
    }


}
