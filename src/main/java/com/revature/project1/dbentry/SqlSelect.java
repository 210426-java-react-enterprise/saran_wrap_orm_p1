package com.revature.project1.dbentry;

public class SqlSelect extends SqlCrud {

    public String selectObject(Object obj) throws IllegalAccessException {
        this.obj = obj;
        setClazz();
        //Gets the class of the obj and uses the annotation to get the table name
        setTableName();

        //Get all the fields in the pojo
        setFields();

        //statement = "select {items} from {tableName} where ({condition})"; //format
        statement = String.format("select %s from %s where (%s)", fields, tableName, condition);
        return statement;
    }
}
