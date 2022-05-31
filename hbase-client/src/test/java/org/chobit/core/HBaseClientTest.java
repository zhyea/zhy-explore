package org.chobit.core;

import org.chobit.core.model.TableRow;
import org.junit.FixMethodOrder;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.chobit.core.Config.NAMESPACE;
import static org.chobit.core.Config.ZK_HOST;
import static org.junit.runners.MethodSorters.DEFAULT;

/**
 * @author zhangrui137
 */
@FixMethodOrder(DEFAULT)
public class HBaseClientTest {


    private final HBaseClient client = new HBaseClient(ZK_HOST, NAMESPACE);


    private final String rowKey1 = "b47dd991c0b34dac907a32f615ddacfd";

    private final String rowKey2 = "345c985cf41242c1851c0f5720492a74";

    private static final String TABLE_CLICK = "growth_channel_click";


    @Test
    public void putRow() {
        TableRow row = new TableRow(rowKey1);
        row.addColumn("c", "j", json());
        row.addColumn("c", "cd", "2022-03-14");
        row.addColumn("c", "ad", "2022-03-15");
        client.put(TABLE_CLICK, row);
    }


    @Test
    public void putColumn() {
        client.put(TABLE_CLICK, rowKey2, "c", "j", "{1122334455}");
    }


    @Test
    public void get() {
        List<String> r1 = client.get(TABLE_CLICK, rowKey1, "c", "j");
        r1.forEach(System.out::println);
        System.out.println("-----");
        List<String> r2 = client.get(TABLE_CLICK, rowKey2, "c", "j");
        r2.forEach(System.out::println);
        System.out.println("---------------------- end get");
    }


    @Test
    public void getFirst() {
        String r1 = client.getFirst(TABLE_CLICK, rowKey1, "c", "j");
        System.out.println(r1);
        ;
        String r2 = client.getFirst(TABLE_CLICK, rowKey2, "c", "j");
        System.out.println(r2);
        System.out.println("---------------------- end getFirst");
    }


    @Test
    public void multiGet() {
        List<String> rowKeys = new LinkedList<>();
        rowKeys.add(rowKey1);
        rowKeys.add(rowKey2);
        List<List<String>> r = client.multiGet(TABLE_CLICK, rowKeys, "c", "j");
        for (List<String> list : r) {
            for (String s : list) {
                System.out.println(s);
            }
            System.out.println("-----");
        }
        System.out.println("---------------------- end multiGet");
    }


    @Test
    public void multiGetFirst() {
        List<String> rowKeys = new LinkedList<>();
        rowKeys.add(rowKey1);
        rowKeys.add(rowKey2);
        List<String> r = client.multiGetFirst(TABLE_CLICK, rowKeys, "c", "j");
        for (String s : r) {
            System.out.println(s);
        }
        System.out.println("---------------------- end multiGetFirst");
    }


    private String json() {
        return "{\"id\":26502568,\"name\":\"robin\",\"site\":\"www.zhyea.com\"}";
    }

}
