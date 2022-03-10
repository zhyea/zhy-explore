package org.chobit.core;

import org.chobit.core.tools.PropKit;

import static org.chobit.core.tools.ToolKit.uuid;

/**
 * @author zhangrui137
 */
public class MyApp {


    private static final String TABLE_CLICK = "growth_channel_click";


    public static void main(String[] args) {
        String zkHost = PropKit.getProp("zk-host");
        String namespace = PropKit.getProp("namespace");

        HBaseClient client = new HBaseClient(zkHost, namespace);

        String rowKey = uuid();
        client.put(TABLE_CLICK, rowKey, "c", "j", "{aaa}");

        System.out.println(rowKey);

        String data = client.getFirst(TABLE_CLICK, rowKey, "c", "j");

        System.out.println(data);

        System.out.println(zkHost);
    }


    static {
        PropKit.load("/config.properties");
    }

}
