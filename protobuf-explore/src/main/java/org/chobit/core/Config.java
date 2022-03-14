package org.chobit.core;


import org.chobit.core.tools.PropKit;

import java.util.LinkedList;
import java.util.List;

import static org.chobit.core.tools.PropKit.getProp;


public final class Config {


    static {
        PropKit.load("/config.properties");
    }


    public static final String RTA_DEV = getProp("rta.dev");


    public static final String RTA_TMP = getProp("rta.tmp");


    public static final String RTA_PROD = getProp("rta.prod");


    public static final String RTA_TEST = getProp("rta.test");

    public static final List<Long> PROD_RTA_IDS = toList(getProp("rta.prod.ids"));

    public static final List<Long> TEST_RTA_IDS = toList(getProp("rta.dev.ids"));

    public static final String TEST_IMEI = getProp("rta.data.imei");

    public static final String TEST_IDFA = getProp("rta.data.idfa");


    private static List<Long> toList(String str) {
        List<Long> result = new LinkedList<>();
        String[] arr = str.split(",");
        for (String s : arr) {
            result.add(Long.parseLong(s));
        }
        return result;
    }

    private Config() {

    }

}
