package com.enovell.yunwei.entity;

/**
 * Created by Administrator on 2017/6/8.
 */
public class TableName {
    private String tableName;

    public String getTableName() {
        return tableName;
    }
    public TableName(String tableName){
        this.tableName = tableName;
    }
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public String toString() {
        return tableName;
    }

    @Override
    public int hashCode() {
        return tableName.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return tableName.equals(obj);
    }
}
