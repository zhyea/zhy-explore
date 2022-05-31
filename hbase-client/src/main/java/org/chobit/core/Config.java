package org.chobit.core;

import org.chobit.core.tools.PropKit;

/**
 * @author zhangrui137
 */
public final class Config {


    static {
        PropKit.load("/config.properties");
    }


    public static final String ZK_HOST = PropKit.getProp("zk-host");


    public static final String NAMESPACE = PropKit.getProp("namespace");


    private Config() {
    }


}
