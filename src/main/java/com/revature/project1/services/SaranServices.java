package com.revature.project1.services;

import com.revature.project1.dbentry.*;
import com.revature.project1.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SaranServices {

    Connection conn;
    SqlCreation sql;

    public SaranServices(SqlCreation sql){
        conn = ConnectionFactory.getInstance().getConnection();
        System.out.println(conn);
        this.sql = sql;
    }

    public SaranServices(SqlCreation sql, Connection conn){
        this.conn = conn;
        this.sql = sql;
    }


    //Inserting one obj into a database
    public String insertObject(Object obj){

        if(obj == null){
            throw new NullPointerException("Null objects can't be put into the database");
        }
        sql.insertNewObject(obj, conn);

        return sql.getStatement();
    }

    //Updating one row/object in a database
    public String updateObject(Object obj, String key, String value) {

        if(obj == null){
            throw new NullPointerException("Null objects can't be updated in the database");
        }

        sql.setCondition(key, value);
        sql.update(obj, conn);
        return sql.getStatement();
    }

    public String updateObject(Object obj, String condition) {

        if(obj == null){
            throw new NullPointerException("Null objects can't be updated in the database");
        }

        sql.setCondition(condition);
        sql.update(obj, conn);
        return sql.getStatement();
    }

    public <T> ArrayList<T> selectObject(Class<T> obj, String condition){

        if(obj == null){
            throw new NullPointerException("Can't determine the obj container for db because of a null class");
        }

        ArrayList<T> DBObjects = new ArrayList<>();

        DBObjects = sql.select(obj, condition, conn);

        return DBObjects;
    }

    public <T> ArrayList<T> selectAllObjects(Class<T> obj){

        if(obj == null){
            throw new NullPointerException("Can't determine the obj container for db because of a null class");
        }

        ArrayList<T> allDBObjects = new ArrayList<>();

        allDBObjects = sql.selectAll(obj, conn);

        System.out.println(allDBObjects);
        return allDBObjects;
    }

    public <T> String deleteObject(Class<T> obj, String condition){

        if(obj == null){
            throw new NullPointerException("Can't determine the obj container for db because of a null class");
        }

        String rowsDeleted = sql.delete(obj, condition, conn);

        return rowsDeleted;

    }

    public ResultSet customCommand(String command) {
        return sql.customSqlCommand(command, conn);
    }

}
