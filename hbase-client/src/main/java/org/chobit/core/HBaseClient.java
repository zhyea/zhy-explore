package org.chobit.core;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.chobit.core.model.TableColumn;
import org.chobit.core.model.TableRow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static org.chobit.core.tools.ToolKit.isEmpty;
import static org.chobit.core.tools.ToolKit.isNotBlank;

/**
 * @author zhangrui137
 */
public class HBaseClient {


    private static final Logger logger = LoggerFactory.getLogger(HBaseClient.class);


    /**
     * 连接信息
     */
    private final Connection conn;
    private final String namespace;


    public HBaseClient(String hBaseZkCluster, String namespace) {
        this.conn = createConnect(hBaseZkCluster);
        this.namespace = namespace;
    }

    /**
     * 删除记录
     */
    public void delete(String tableName, String rowKey) {
        try {
            Delete delete = new Delete(Bytes.toBytes(rowKey));
            Table table = tableOf(tableName);
            table.delete(delete);
        } catch (Exception e) {
            logger.error("delete row from hbase error. table:[{}], rowKey:[{}]", tableName, rowKey, e);
        }
    }

    /**
     * 向MySQL中写入记录
     */
    public void put(String tableName, TableRow row) {
        try {
            Put put = new Put(Bytes.toBytes(row.getRowKey()));
            for (TableColumn column : row.getColumns()) {
                byte[] cf = Bytes.toBytes(column.getColumnFamily());
                byte[] qa = Bytes.toBytes(column.getQualifier());
                byte[] vl = Bytes.toBytes(column.getValue());
                put.addColumn(cf, qa, vl);
            }
            Table table = tableOf(tableName);
            table.put(put);
        } catch (Exception e) {
            logger.error("put into hbase error. table:[{}], row:{}", tableName, row, e);
            e.printStackTrace();
        }
    }


    /**
     * 更新记录
     */
    public void put(String tableName, String rowKey, String columnFamily, String qualifier, String value) {
        this.put(tableName, rowKey, columnFamily, qualifier, Bytes.toBytes(value));
    }


    /**
     * 更新记录
     */
    public void put(String tableName, String rowKey, String columnFamily, String qualifier, byte[] value) {
        try {
            Put put = new Put(Bytes.toBytes(rowKey));
            put.addColumn(Bytes.toBytes(columnFamily), Bytes.toBytes(qualifier), value);
            Table table = tableOf(tableName);
            table.put(put);
        } catch (Exception e) {
            logger.error("put into hbase error. table:[{}], row:[{}:{}:{}]", tableName, rowKey, columnFamily, qualifier, e);
            e.printStackTrace();
        }
    }


    public String getFirst(String tableName, String rowKey, String columnFamily, String qualifier) {
        List<String> list = get(tableName, rowKey, columnFamily, qualifier);
        if (!isEmpty(list)) {
            return list.get(0);
        }
        return null;
    }


    public List<String> multiGetFirst(String tableName, List<String> rowKeys, String columnFamily, String qualifier) {
        List<List<String>> list = multiGet(tableName, rowKeys, columnFamily, qualifier);
        List<String> result = new LinkedList<>();
        for (List<String> l : list) {
            if (isEmpty(l)) {
                continue;
            }
            result.add(l.get(0));
        }
        return result;
    }

    /**
     *
     */
    public List<String> get(String tableName, String rowKey, String columnFamily, String qualifier) {
        try {
            Get get = buildGet(rowKey, columnFamily, qualifier);
            Table table = tableOf(tableName);

            Result r = table.get(get);
            return readResult(r);
        } catch (IOException e) {
            logger.error("get from hbase error. table:[{}], row:[{}:{}:{}]", tableName, rowKey, columnFamily, qualifier, e);
            e.printStackTrace();
        }
        return null;
    }


    public List<List<String>> multiGet(String tableName, List<String> rowKeys, String columnFamily, String qualifier) {
        List<List<String>> result = new LinkedList<>();
        try {
            List<Get> gets = new LinkedList<>();
            for (String rowKey : rowKeys) {
                Get get = buildGet(rowKey, columnFamily, qualifier);
                gets.add(get);
            }

            Table table = tableOf(tableName);
            Result[] results = table.get(gets);

            for (Result r : results) {
                List<String> l = readResult(r);
                result.add(l);
            }
        } catch (Exception e) {
            logger.error("multi get from hbase error. table:[{}], rowKeys:{}, column:[{}:{}]", tableName, rowKeys, columnFamily, qualifier, e);
            e.printStackTrace();
        }
        return result;
    }


    private List<String> readResult(Result r) {
        List<String> result = new LinkedList<>();
        List<Cell> cells = r.listCells();
        if (!isEmpty(cells)) {
            for (Cell c : cells) {
                String v = Bytes.toString(c.getValueArray(), c.getValueOffset(), c.getValueLength());
                result.add(v);
            }
        }
        return result;
    }


    private Get buildGet(String rowKey, String columnFamily, String qualifier) {
        Get get = new Get(Bytes.toBytes(rowKey));
        if (isNotBlank(columnFamily)) {
            get.addFamily(Bytes.toBytes(columnFamily));
            if (isNotBlank(qualifier)) {
                get.addColumn(Bytes.toBytes(columnFamily), Bytes.toBytes(qualifier));
            }
        }
        return get;
    }


    private Table tableOf(String tableName) throws IOException {
        String t = this.namespace + ":" + tableName;
        return this.conn.getTable(TableName.valueOf(t));
    }

    /**
     * 创建连接
     */
    private Connection createConnect(String zkCluster) {
        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", zkCluster);
        conf.set("hbase.zookeeper.property.clientPort", "2181");
        conf.set("hbase.client.ipc.pool.type", "RoundRobin");
        conf.set("hbase.client.ipc.pool.size", "15");
        conf.set("hbase.client.retries.number", "3");
        conf.set("hbase.client.operation.timeout", "3000");
        conf.set("hbase.client.scanner.timeout.period", "10000");
        conf.set("hbase.client.pause", "50");
        conf.set("hbase.rpc.timeout", "2000");
        try {
            Connection conn = ConnectionFactory.createConnection(conf);
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    System.out.println("closing hbase connection...");
                    conn.close();
                    System.out.println("hbase connection closed.");
                } catch (IOException e) {
                    logger.error("关闭HBase连接失败", e);
                }
            }));
            return conn;
        } catch (IOException e) {
            throw new RuntimeException("Connect to HBase cluster error.", e);
        }
    }


}
