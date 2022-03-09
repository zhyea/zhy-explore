package org.chobit.core.model;

/**
 * @author zhangrui137
 */
public class TableColumn {

    private String columnFamily;

    private String qualifier;

    private String value;

    public String getColumnFamily() {
        return columnFamily;
    }

    public void setColumnFamily(String columnFamily) {
        this.columnFamily = columnFamily;
    }

    public String getQualifier() {
        return qualifier;
    }

    public void setQualifier(String qualifier) {
        this.qualifier = qualifier;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


    @Override
    public String toString() {
        return "TableColumn{" +
                "columnFamily='" + columnFamily + '\'' +
                ", qualifier='" + qualifier + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
