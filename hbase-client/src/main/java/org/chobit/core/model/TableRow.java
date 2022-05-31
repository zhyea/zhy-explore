package org.chobit.core.model;

import java.util.LinkedList;
import java.util.List;

/**
 * @author zhangrui137
 */
public class TableRow {

    private String rowKey;

    private final List<TableColumn> columns = new LinkedList<>();

    public TableRow() {
    }

    public TableRow(String rowKey) {
        this.rowKey = rowKey;
    }

    public void addColumn(TableColumn column) {
        this.columns.add(column);
    }

    public void addColumn(String columnFamily, String qualifier, String value) {
        TableColumn column = new TableColumn();
        column.setColumnFamily(columnFamily);
        column.setQualifier(qualifier);
        column.setValue(value);
        addColumn(column);
    }

    public String getRowKey() {
        return rowKey;
    }

    public void setRowKey(String rowKey) {
        this.rowKey = rowKey;
    }

    public List<TableColumn> getColumns() {
        return columns;
    }


    @Override
    public String toString() {
        return "TableRow{" +
                "rowKey='" + rowKey + '\'' +
                ", columns=" + columns +
                '}';
    }
}
