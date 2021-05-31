package com.revature.project1.services;

import com.revature.project1.dbentry.*;
import com.revature.project1.util.ConnectionFactory;
import java.sql.Connection;
import java.util.ArrayList;

public class SaranServices {

    Connection conn;
    SqlCreation sql;

    public SaranServices(SqlCreation sql){
        conn = ConnectionFactory.getInstance().getConnection();
        System.out.println(conn);
        this.sql = sql;
    }


    //Inserting one obj into a database

    public String insertInDB(Object obj){

        sql.insertNewObject(obj, conn);

        //AppUser test = (AppUser) obj;

        return sql.getStatement();
    }

    //Updating one row/object in a database
    public String updateObject(Object obj, String key, String value) {

        sql.setCondition(key, value);
        sql.update(obj, conn);
        return sql.getStatement();
    }

    public String updateObject(Object obj, String condition) {

        sql.setCondition(condition);
        sql.update(obj, conn);
        return sql.getStatement();
    }

    public <T> ArrayList<T> SelectDB(Class<T> obj, String condition){

        ArrayList<T> DBObjects = new ArrayList<>();

        DBObjects = sql.select(obj, condition, conn);

        return DBObjects;
    }

    public <T> ArrayList<T> SelectAllDB(Class<T> obj){

        ArrayList<T> allDBObjects = new ArrayList<>();

        allDBObjects = sql.selectAll(obj, conn);

        System.out.println(allDBObjects);
        return allDBObjects;
    }

    public <T> String deleteDB(Class<T> obj, String condition){

        String rowsDeleted = sql.delete(obj, condition, conn);

        return rowsDeleted;

    }



}
