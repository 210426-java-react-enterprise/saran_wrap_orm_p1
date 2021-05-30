package com.revature.project1.services;

import com.revature.project1.dbentry.SqlDelete;
import com.revature.project1.dbentry.SqlInsert;

import com.revature.project1.models.AppUser;
import com.revature.project1.dbentry.SqlSelect;
import com.revature.project1.util.ConnectionFactory;

import java.sql.Connection;
import java.util.ArrayList;

public class SaranServices {

    Connection conn;

    public SaranServices(){

    }

    //Inserting one obj into a database
    public void insertInDB(Object obj){

        if(obj == null){
            throw new NullPointerException();
        }

        SqlInsert insertTest = new SqlInsert();

        try {
            insertTest.testNothing("test");
            insertTest.insertNewObject(obj, conn);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return;
    }

    public <T> ArrayList<T> SelectDB(Class<T> obj, String condition){

        if(obj == null){
            throw new NullPointerException();
        }

        SqlSelect selectTest = new SqlSelect();
        ArrayList<T> DBObjects = new ArrayList<>();

        try {
            DBObjects = selectTest.select(obj, condition, conn);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return DBObjects;
    }

    public <T> ArrayList<T> SelectAllDB(Class<T> obj){

        if(obj == null){
            throw new NullPointerException();
        }

        SqlSelect selectTest = new SqlSelect();
        ArrayList<T> allDBObjects = new ArrayList<>();

        try {
            allDBObjects = selectTest.selectAll(obj, conn);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return allDBObjects;
    }

    public <T> String deleteDB(Class<T> obj, String condition){

        if(obj == null){
            throw new NullPointerException();
        }

        SqlDelete sqlDelete = new SqlDelete();

        String rowsDeleted = sqlDelete.delete(obj, condition, conn);

        return rowsDeleted;

    }

}
